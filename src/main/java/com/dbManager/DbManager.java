package com.dbManager;
import com.entities.Contributor;
import com.entities.Concept;
import org.json.JSONObject;
import org.json.JSONArray;


import java.sql.*;
import java.util.ArrayList;

public class DbManager {
    final static  String FILE_NAME = "CashFair/src/main/java/com/db/CashFairDb.db";
    final static String STMT_INSERT_CONCEPT =
            "INSERT INTO concepts (currency, date, concept_name, contributors) " +
            "VALUES (?, ?, ?, ?)";
    final static String STMT_INSERT_CONTRIBUTOR =
            "UPDATE concepts " +
            "SET contributors = ? " +
            "WHERE concept_name = ?";
    final static String QUERY_GET_CONC_NAMES = "SELECT concept_name FROM concepts";
    final static String QUERY_GET_CONC_BY_CONC_NAME = "SELECT * FROM concepts WHERE concept_name =  ?";
    static Connection conn;
    private static void establishDbConnection() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:"+FILE_NAME);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void closeDbConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertData(Concept concept) {
        establishDbConnection();
        try {
            insertConcept(concept);
            insertContributor(concept);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        closeDbConnection();
    }

    public ArrayList getConceptNames() {
        establishDbConnection();
        ArrayList<String> listConcepts = new ArrayList<String>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery(QUERY_GET_CONC_NAMES);
            while (rset.next()) {
                listConcepts.add(rset.getString("concept_name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        closeDbConnection();
        return listConcepts;
    }

    public Concept getConcept(String conceptNameSent) {
        establishDbConnection();
        Concept concept = new Concept(null, null, null, null);
        try {
            PreparedStatement pstmt = conn.prepareStatement(QUERY_GET_CONC_BY_CONC_NAME);
            pstmt.setString(1, conceptNameSent);
            ResultSet rset = pstmt.executeQuery();
            if (rset.next()) {
                ArrayList<Contributor> listContributors = new ArrayList<Contributor>();
                String contributors =  rset.getString("contributors");
                String currency = rset.getString("currency");
                String date = rset.getString("date");
                String conceptName = rset.getString("concept_name");

                JSONArray jsonArray = new JSONArray(contributors);
                JSONObject obj = new JSONObject();
                Double money;
                Double percentage;
                String name;
                for (int i = 0; i < jsonArray.length(); i++) {
                    obj = jsonArray.getJSONObject(i);
                    name = obj.getString("name");
                    money = obj.getDouble("money");
                    percentage = obj .getDouble("percentage");
                    listContributors.add(new Contributor(name, money, percentage));
                }
                concept = new Concept(listContributors, currency, date, conceptName);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        closeDbConnection();
        return concept;
    }
    private void insertContributor(Concept concept) throws SQLException {
        JSONArray listContributors = new JSONArray();
        for (Contributor contributor: concept.getListContributor()) {
            JSONObject newContributor = new JSONObject();
            newContributor.put("name", contributor.getName());
            newContributor.put("money", contributor.getMoney());
            newContributor.put("percentage", contributor.getPercentage());
           listContributors.put(newContributor);
        }
        PreparedStatement pstmt = conn.prepareStatement(STMT_INSERT_CONTRIBUTOR);
        pstmt.setObject(1, listContributors);
        pstmt.setString(2, concept.getConceptName());
        pstmt.executeUpdate();
    }

    private void insertConcept(Concept concept) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(STMT_INSERT_CONCEPT);
        pstmt.setString(1, concept.getCurrency());
        pstmt.setString(2, concept.getDate());
        pstmt.setString(3, concept.getConceptName());
        pstmt.setString(4, "");
        pstmt.executeUpdate();
    }


}
