package icenterdata;

public class DataReader {
    private static DataReader dataReader = null;

    private DataReader() {

    }

    public static DataReader DataReader() {
        if (dataReader == null ) {
            dataReader = new DataReader();
        }

        return dataReader;
    }
}
