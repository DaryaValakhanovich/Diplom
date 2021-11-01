import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AthleteDAO {
    public List<Athlete> findAll(){
        List<Athlete> list = new ArrayList<>();
        try (ResultSet resultSet = ConnectionManager.getConnection()
                .prepareStatement(getSelectAllQuery()).executeQuery()) {
            while (resultSet.next()) {
                list.add(createFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Athlete findById(long id) {
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection()
                .prepareStatement(getSelectQueryById())) {
            preparedStatement.setLong(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(createFromResultSet(resultSet)).get();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Athlete();
    }

    public Athlete create(Athlete object) {
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection()
                .prepareStatement(getCreateQuery(), Statement.RETURN_GENERATED_KEYS)) {
            prepareStatementForCreate(preparedStatement, object);
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                object.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return object;
    }

    public Athlete update(Athlete object) {
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection()
                .prepareStatement(getUpdateQuery())) {
            prepareStatementForCreate(preparedStatement, object);
            preparedStatement.setLong(9, object.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return object;
    }

    private static final String ATHLETE_TABLE_NAME = "ATHLETES";


    private AthleteDAO() { }

    private static final class AthleteDAOHolder {
        static final AthleteDAO INSTANCE = new AthleteDAO();
    }

    public static AthleteDAO getInstance() {
        return AthleteDAOHolder.INSTANCE;
    }

    public String getSelectQueryById() {
        return "SELECT * FROM ATHLETES " +
                "WHERE id = ?";
    }

    public String getSelectQueryForCsvFiles() {
        return "SELECT * FROM CSVFILES " +
                "WHERE AthleteId = ?";
    }

    public String getSelectAllQuery() {
        return "SELECT * FROM ATHLETES;";
    }

    public String getCreateQuery() {
        return "INSERT INTO ATHLETES (name, surname, patronymic, age, gender, sport, \n" +
                "hand, qualification) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    }

    public String getUpdateQuery() {
        return "UPDATE ATHLETES SET name=?, surname=?, patronymic=?, age=?, gender=?, sport=?, " +
                "hand=?, qualification=? where id = ?";
    }


    protected void prepareStatementForCreate(PreparedStatement statement, Athlete object) throws SQLException {
        statement.setString(1, object.getName());
        statement.setString(2, object.getSurname());
        statement.setString(3, object.getPatronymic());
        statement.setInt(4, object.getAge());
        statement.setString(5, object.getGender().toString());
        statement.setString(6, object.getSport());
        statement.setString(7, object.getDominantHand().toString());
        statement.setString(8, object.getQualification().toString());
    }

    public Athlete createFromResultSet(ResultSet resultSet) throws SQLException {
        Athlete athlete = new Athlete();
        athlete.setId(resultSet.getLong(ATHLETE_TABLE_NAME + ".id"));
        athlete.setName(resultSet.getString(ATHLETE_TABLE_NAME + ".name"));
        athlete.setSurname(resultSet.getString(ATHLETE_TABLE_NAME + ".surname"));
        athlete.setPatronymic(resultSet.getString(ATHLETE_TABLE_NAME + ".patronymic"));
        athlete.setAge(resultSet.getInt(ATHLETE_TABLE_NAME + ".age"));
        athlete.setGender(Gender.valueOf(resultSet.getString(ATHLETE_TABLE_NAME + ".gender")));
        athlete.setSport(resultSet.getString(ATHLETE_TABLE_NAME + ".sport"));
        athlete.setDominantHand(DominantHand.valueOf(resultSet.getString(ATHLETE_TABLE_NAME + ".hand")));
        athlete.setQualification(Qualification.valueOf(resultSet.getString(ATHLETE_TABLE_NAME + ".qualification")));
        return  athlete;
    }

    public void delete(long id) {
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection()
                .prepareStatement("DELETE * FROM ATHLETES " +
                        "WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addCsvFile(long athleteId, String fileName){
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection()
                .prepareStatement("INSERT INTO csvfiles (name, AthletId) VALUES (?, ?)")) {
            preparedStatement.setString(1, fileName);
            preparedStatement.setLong(2, athleteId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> findAllCsvFiles(){
        List<String> list = new ArrayList<>();
        try (ResultSet resultSet = ConnectionManager.getConnection()
                .prepareStatement(getSelectAllQuery()).executeQuery()) {
            while (resultSet.next()) {
                list.add(resultSet.getString( "CSVFILES.name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
