package system.service;

import org.springframework.stereotype.Service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Alex on 03.04.2017.
 */

@Service
public class DBService {
    private static final String CONNECTION_URL_WIN = "jdbc:sqlserver://localhost:49172;databaseName=schedule_typed;" +
            "user=admin;password=****;integratedSecurity=true;";

    private static final String CONNECTION_URL_UBNT = "jdbc:sqlserver://localhost:1433;databaseName=schedule_typed;" +
            "user=SA;password=****;";

    private static final String AZURE_CONNECTION_URL = "jdbc:sqlserver://midnight.database.windows.net:1433;" +
            "database=schedule_cloud;user=alex@midnight;password=*******;" +
            "encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";




    public int insertSchedule(String values) {
        CallableStatementImp callableStatement = new CallableStatementImp();
        try (Connection connection = callableStatement.createConnection()) {
            // Creating Callable Statement
            String query = String.format("INSERT INTO SCHEDULE VALUES (%s);",  values);
            CallableStatement cs = connection.prepareCall(query);
            cs.execute();
            if (cs.getUpdateCount() > 0){
                return 1;
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return 0;
    }


    public Integer getLessonCount(String teacherId){
        CallableStatementImp callableStatement = new CallableStatementImp();
        Integer numberOfLessons = null;
        try (Connection connection = callableStatement.createConnection()) {
            // Creating Callable Statement
            String query = String.format("SELECT COUNT(teacher_id) FROM SCHEDULE WHERE teacher_id=\'%s\';", teacherId);
            CallableStatement cs = connection.prepareCall(query);
            ResultSet rs = cs.executeQuery();
            // checking result
            if (rs == null) {
                throw new DBException("result is null", new DBException());
            } else {
                while (rs.next()) {

                       numberOfLessons = rs.getInt(1);

                }

            }
            return numberOfLessons;
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }

    }


    public ArrayList<String> getForTabThree(){
        CallableStatementImp callableStatement = new CallableStatementImp();
        try (Connection connection = callableStatement.createConnection()) {
            ArrayList<String> resList = null;
            // Creating Callable Statement
            String query = "SELECT * FROM LESSON_STUDY_GROUP";
            CallableStatement cs = connection.prepareCall(query);
            ResultSet rs = cs.executeQuery();
            // checking result
            if (rs == null) {
                throw new DBException("result is null", new DBException());
            } else {
                resList = new ArrayList<>();
                while (rs.next()) {
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        resList.add(rs.getString(i));
                    }
                }
            }
            return resList;
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }



    public HashMap<String, String> getForTabFourTeacher(){
        CallableStatementImp callableStatement = new CallableStatementImp();
        try (Connection connection = callableStatement.createConnection()) {
            HashMap<String, String> result = null;
            StringBuilder stringBuilder = null;
            // Creating Callable Statement
            String query = "SELECT * FROM TEACHER ORDER BY last_name";
            CallableStatement cs = connection.prepareCall(query);
            ResultSet rs = cs.executeQuery();
            // checking result
            if (rs == null) {
                throw new DBException("result is null", new DBException());
            } else {
                result = new HashMap<>();
                stringBuilder = new StringBuilder();
                //заполняем StringBuilder ФИО , в hashmap заполняем ключи  из strb , а value teacher_id
                while (rs.next()) {
//                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        stringBuilder.append(rs.getString("last_name")).append(" ").
                                append(rs.getString("first_name")).append(" ").append(rs.getString("middle_name"));
                        result.put(stringBuilder.toString(), String.valueOf(rs.getInt("teacher_id")));
                        stringBuilder.delete(0, stringBuilder.length());
//                    }
                }
            }
            return result;
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }


    public HashMap<Integer, Integer> getForTabFourClassroom(){
        CallableStatementImp callableStatement = new CallableStatementImp();
        try (Connection connection = callableStatement.createConnection()) {
            HashMap<Integer, Integer> result = null;
            // Creating Callable Statement
            String query = "SELECT * FROM CLASSROOM ORDER BY classroom_number";
            CallableStatement cs = connection.prepareCall(query);
            ResultSet rs = cs.executeQuery();
            // checking result
            if (rs == null) {
                throw new DBException("result is null", new DBException());
            } else {
                result = new HashMap<>();
                while (rs.next()) {
                    result.put(rs.getInt("classroom_number"), rs.getInt("classroom_id"));
                }
            }
            return result;
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }

    public ArrayList<String> getForTabFourLesson(){
        CallableStatementImp callableStatement = new CallableStatementImp();
        try (Connection connection = callableStatement.createConnection()) {
            ArrayList<String> resList = null;
            // Creating Callable Statement
            String query = "SELECT * FROM LESSON";
            CallableStatement cs = connection.prepareCall(query);
            ResultSet rs = cs.executeQuery();
            // checking result
            if (rs == null) {
                throw new DBException("result is null", new DBException());
            } else {
                resList = new ArrayList<>();
                while (rs.next()) {
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        resList.add(rs.getString(i));
                    }
                }
            }
            return resList;
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }



    public ArrayList<String> getForTabFourGroup(){
        CallableStatementImp callableStatement = new CallableStatementImp();
        try (Connection connection = callableStatement.createConnection()) {
            ArrayList<String> resList = null;
            // Creating Callable Statement
            String query = "SELECT DISTINCT group_number FROM STUDY_GROUP";
            CallableStatement cs = connection.prepareCall(query);
            ResultSet rs = cs.executeQuery();
            // checking result
            if (rs == null) {
                throw new DBException("result is null", new DBException());
            } else {
                resList = new ArrayList<>();
                while (rs.next()) {
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        resList.add(rs.getString(i));
                    }
                }
            }
            return resList;
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }


    public ArrayList<String> getForTabTwo(){
        CallableStatementImp callableStatement = new CallableStatementImp();
        try (Connection connection = callableStatement.createConnection()) {
            ArrayList<String> resList = null;
            // Creating Callable Statement
            String query = "SELECT  TEACHER.last_name, TEACHER.first_name, TEACHER.middle_name, TEACHER_LESSON.lesson_name FROM TEACHER, TEACHER_LESSON\n" +
                    "WHERE TEACHER_LESSON.teacher_id = TEACHER.teacher_id ORDER BY TEACHER.last_name ASC";
            CallableStatement cs = connection.prepareCall(query);
            ResultSet rs = cs.executeQuery();
            // checking result
            if (rs == null) {
                throw new DBException("result is null", new DBException());
            } else {
                resList = new ArrayList<>();
                while (rs.next()) {
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        resList.add(rs.getString(i));
                    }
                }
            }
            return resList;
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }


    public ArrayList<String> getForTabOne(){
        CallableStatementImp callableStatement = new CallableStatementImp();
        try (Connection connection = callableStatement.createConnection()) {
            ArrayList<String> resList = null;
            // Creating Callable Statement
            String query = "SELECT LESSON_CLASSROOM.lesson_name, CLASSROOM.classroom_number FROM LESSON_CLASSROOM," +
                    " CLASSROOM WHERE LESSON_CLASSROOM.classroom_id = CLASSROOM.classroom_id";
            CallableStatement cs = connection.prepareCall(query);
            ResultSet rs = cs.executeQuery();
            // checking result
            if (rs == null) {
                throw new DBException("result is null", new DBException());
            } else {
                resList = new ArrayList<>();
                while (rs.next()) {
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        resList.add(rs.getString(i));
                    }
                }
            }
            return resList;
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }




    public ArrayList<String> getSchedule(String group_num, String day_name){
        CallableStatementImp callableStatement = new CallableStatementImp();
        try (Connection connection = callableStatement.createConnection()) {
            ArrayList<String> resList = null;
            // Creating Callable Statement
            String query = String.format("SELECT SCHEDULE.day_name, SCHEDULE.lesson_number, SCHEDULE.begin_time, SCHEDULE.end_time,\n" +
                    "SCHEDULE.group_number, CORPUS.corpus_adress, CLASSROOM.classroom_number, SCHEDULE.lesson_name,\n" +
                    "SCHEDULE.lesson_type_name, TEACHER.last_name, TEACHER.first_name, TEACHER.middle_name\n" +
                    "FROM SCHEDULE, CORPUS, CLASSROOM, TEACHER\n" +
                    "WHERE group_number = \'%s\' AND day_name = \'%s\' AND SCHEDULE.corpus_id=CORPUS.corpus_id\n" +
                    "AND SCHEDULE.classroom_id=CLASSROOM.classroom_id AND  SCHEDULE.teacher_id=TEACHER.teacher_id ORDER BY SCHEDULE.lesson_number ASC", group_num, day_name);
            CallableStatement cs = connection.prepareCall(query);
            ResultSet rs = cs.executeQuery();
            // checking result
            if (rs == null) {
                throw new DBException("result is null", new DBException());
            } else {
                resList = new ArrayList<>();
                while (rs.next()) {
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        resList.add(rs.getString(i));
                    }
                }
            }
            return resList;
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }




    /*

    public void delete(String tableName, String conditions) {
        CallableStatementImp callableStatement = new CallableStatementImp();
        try (Connection connection = callableStatement.createConnection()) {
            // Creating Callable Statement
            String query = String.format("DELETE FROM %s WHERE %s);", tableName, conditions);
            CallableStatement cs = connection.prepareCall(query);
            cs.execute();

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void update(String tableName, String conditions, String... setParams) {
        CallableStatementImp callableStatement = new CallableStatementImp();
        try (Connection connection = callableStatement.createConnection()) {
            // Creating Callable Statement
            String query = String.format("SELECT * FROM %s;", tableName);
            CallableStatement csSelect = connection.prepareCall(query);
            ResultSet rs = csSelect.executeQuery();
            // checking result
            if (rs == null) {
                throw new DBException("table is empty", new Exception());
            }

            if (setParams.length != rs.getMetaData().getColumnCount()) {
                throw new IllegalArgumentException();
            }

            StringBuilder strb = new StringBuilder(String.format("UPDATE %s SET ", tableName));
            for (int i = 1; i <= setParams.length; i++) {
                strb.append(String.format("%s = '%s', ", rs.getMetaData().getColumnName(i), setParams[i]));
            }
            strb.append(conditions);
            query = strb.toString();

            CallableStatement csUpdate = connection.prepareCall(query);
            csUpdate.execute();

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }*/

      /*
    public String select(String what, String from, String... optional) {
        CallableStatementImp callableStatement = new CallableStatementImp();
        try (Connection connection = callableStatement.createConnection()) {
            StringBuilder strb = null;
            // Creating Callable Statement
            StringBuilder optionalState = new StringBuilder("");
            Arrays.asList(optional).forEach(optionalState::append);
            String query = String.format("SELECT %s FROM %s %s;", what, from, optionalState.toString());
            CallableStatement cs = connection.prepareCall(query);
            ResultSet rs = cs.executeQuery();
            // checking result
            if (rs == null) {
                throw new DBException("result is null", new DBException());
            } else {
                strb = new StringBuilder();
                while (rs.next()) {
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        strb.append(rs.getString(i)).append(" ");
                    }
                    strb.append("\n");
                }
            }
            return strb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }*/


    private static class CallableStatementImp {
        private Connection connection;

        public CallableStatementImp() {
            try {
                // Loading the driver
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            } catch (ClassNotFoundException e) {
                System.out.println(e.toString());
            }
        }
        // Creating a function to get a connection
        public Connection createConnection() {
            // checking connection
            if (connection != null) {
                System.out.println("Can't create a connection");
                return connection;
            } else {
                try {
                    // Getting connection
                    connection = DriverManager.getConnection(AZURE_CONNECTION_URL);
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
            return connection;
        }
    }
}
