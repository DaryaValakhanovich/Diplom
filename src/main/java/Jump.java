public class Jump {
    private double timestamp;
    private double sync;
    private double aux;
    private double fx;
    private double fy;
    private double mx;
    private double my;
    private double mz;
    private double coPx;
    private double coPy;

    public Jump() { }

    public Jump(double timestamp, double sync, double aux, double fx, double fy, double mx, double my, double mz,
                double coPx, double coPy) {
        this.timestamp = timestamp;
        this.sync = sync;
        this.aux = aux;
        this.fx = fx;
        this.fy = fy;
        this.mx = mx;
        this.my = my;
        this.mz = mz;
        this.coPx = coPx;
        this.coPy = coPy;
    }

    @Override
    public String toString() {
        return "Jump{" +
                "timestamp=" + timestamp +
                ", sync=" + sync +
                ", aux=" + aux +
                ", fx=" + fx +
                ", fy=" + fy +
                ", mx=" + mx +
                ", my=" + my +
                ", mz=" + mz +
                ", coPx=" + coPx +
                ", coPy=" + coPy +
                '}';
    }

    public void setTimestamp(double timestamp) {
        this.timestamp = timestamp;
    }

    public void setSync(double sync) {
        this.sync = sync;
    }

    public void setAux(double aux) {
        this.aux = aux;
    }

    public void setFx(double fx) {
        this.fx = fx;
    }

    public void setFy(double fy) {
        this.fy = fy;
    }

    public void setMx(double mx) {
        this.mx = mx;
    }

    public void setMy(double my) {
        this.my = my;
    }

    public void setMz(double mz) {
        this.mz = mz;
    }

    public void setCoPx(double coPx) {
        this.coPx = coPx;
    }

    public void setCoPy(double coPy) {
        this.coPy = coPy;
    }

    public double getTimestamp() {
        return timestamp;
    }

    public double getSync() {
        return sync;
    }

    public double getAux() {
        return aux;
    }

    public double getFx() {
        return fx;
    }

    public double getFy() {
        return fy;
    }

    public double getMx() {
        return mx;
    }

    public double getMy() {
        return my;
    }

    public double getMz() {
        return mz;
    }

    public double getCoPx() {
        return coPx;
    }

    public double getCoPy() {
        return coPy;
    }
}
