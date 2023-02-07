package gr.upatras.ceid.application.database_connection.action_log_table_connection;

import application.database_connection.DatabaseConnector;
import application.database_models.ActionLog;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import static application.Constants.ACTION_LOG;

public class ActionLogLoader extends DatabaseConnector {

    // -------------------------------------------- Methods --------------------------------------------

    // Loads the action log from the database
    public ActionLog loadActionLog() {
        try {
            String query = "SELECT * FROM action_log;";
            ResultSet result = statement.executeQuery(query);
            ActionLog actionLog = new ActionLog();
            while (result.next()) {
                actionLog.addNewEntry(createActionLogEntryModel(result));
            }
            setSuccessful();
            return actionLog;
        } catch (SQLException exception) {
            exception.printStackTrace();
            setUnsuccessful();
        } finally {
            closeConnection();
        }
        return null;
    }

    // Creates a new action log entry
    private ActionLog.ActionLogEntry createActionLogEntryModel(ResultSet result) throws SQLException {
        Integer idNumber = result.getInt(ACTION_LOG.ID_NUM);
        String user = result.getString(ACTION_LOG.USERNAME);
        String table = result.getString(ACTION_LOG.TABLE_NAME);
        Timestamp dateTime = result.getTimestamp(ACTION_LOG.TIMESTAMP);
        String actionType = result.getString(ACTION_LOG.ACTION_TYPE);
        Integer actionCompleted = result.getInt(ACTION_LOG.SUCCESS);
        return new ActionLog.ActionLogEntry(idNumber, user, table, dateTime, actionType, actionCompleted);
    }
}
