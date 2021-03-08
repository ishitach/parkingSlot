package dao;

/**
 * Created by ishita.chourasia on 07/03/21.
 */
public class DatabaseService {


    private static DatabaseService databaseService;

    private DatabaseService(){

    }

    public static DatabaseService getInstance(){

        if(databaseService == null){
            databaseService = new DatabaseService();
        }
        return databaseService;
    }
}
