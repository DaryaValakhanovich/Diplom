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
     //   try (ResultSet resultSet = ConnectionManager.getConnection()
   //             .prepareStatement(getSelectAllQuery()).executeQuery()) {
      //      while (resultSet.next()) {
      //          list.add(createFromResultSet(resultSet));
       //     }
    //    } catch (SQLException e) {
    //        e.printStackTrace();
    //    }
        return list;
    }

    public Optional<Athlete> findById(long id) {
    //    try (PreparedStatement preparedStatement = ConnectionManager.getConnection()
      //          .prepareStatement(getSelectQueryById())) {
     //       preparedStatement.setLong(1, id);
     //       try(ResultSet resultSet = preparedStatement.executeQuery()) {
      //          if (resultSet.next()) {
       //             return Optional.of(createFromResultSet(resultSet));
       //         }
      //      }
    //    } catch (SQLException e) {
     //       e.printStackTrace();
     //   }
        return Optional.empty();
    }

    public Athlete create(Athlete object) {
    //    try (PreparedStatement preparedStatement = ConnectionManager.getConnection()
      //          .prepareStatement(getCreateQuery(), Statement.RETURN_GENERATED_KEYS)) {
        //    prepareStatementForCreate(preparedStatement, object);
         //   preparedStatement.executeUpdate();
          //  ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
          //  if (generatedKeys.next()) {
            //    object.setId(generatedKeys.getLong(1));
     //       }
       // } catch (SQLException e) {
         //   e.printStackTrace();
        //}
        return object;
    }

    private static final String ATHLETE_TABLE_NAME = "ATHLETES";
    private static AthleteDAO INSTANCE = null;


    private AthleteDAO() { }

    public static AthleteDAO getInstance() {
        if (INSTANCE == null) {
            synchronized (AthleteDAO.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AthleteDAO();
                }
            }
        }
        return INSTANCE;
    }

    public String getSelectQueryById() {
        return "SELECT * FROM ATHLETES " +
                "WHERE id = ?";
    }

    public String getSelectAllQuery() {
        return "SELECT * FROM ATHLETES;";
    }

    public String getCreateQuery() {
        return "INSERT INTO ATHLETES (name, surname, patronymic, birthday, gender, sport, \n" +
                "hand, qualification) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    }

    protected void prepareStatementForCreate(PreparedStatement statement, Athlete object) throws SQLException {
        statement.setString(1, object.getName());
        statement.setString(2, object.getSurname());
        statement.setString(3, object.getPatronymic());
        statement.setString(4, object.getBirthday().toString());
        statement.setString(5, object.getGender().toString());
        statement.setString(6, object.getSport());
        statement.setString(7, object.getDominantHand().toString());
        statement.setString(8, object.getQualification());
    }

    public Athlete createFromResultSet(ResultSet resultSet) throws SQLException {
        Athlete athlete = new Athlete();
        athlete.setId(resultSet.getLong(ATHLETE_TABLE_NAME + ".id"));
        athlete.setName(resultSet.getString(ATHLETE_TABLE_NAME + ".name"));
        athlete.setSurname(resultSet.getString(ATHLETE_TABLE_NAME + ".surname"));
        athlete.setPatronymic(resultSet.getString(ATHLETE_TABLE_NAME + ".patronymic"));
        athlete.setBirthday(LocalDate.parse(resultSet.getString(ATHLETE_TABLE_NAME + ".birthday")));
        athlete.setGender(Gender.valueOf(resultSet.getString(ATHLETE_TABLE_NAME + ".gender")));
        athlete.setSport(resultSet.getString(ATHLETE_TABLE_NAME + ".sport"));
        athlete.setDominantHand(DominantHand.valueOf(resultSet.getString(ATHLETE_TABLE_NAME + ".hand")));
        athlete.setQualification(resultSet.getString(ATHLETE_TABLE_NAME + ".qualification"));
        return  athlete;
    }



}
