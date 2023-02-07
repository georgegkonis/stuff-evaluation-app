package gr.upatras.ceid.application.database_models;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ActionLog {

    public static class ActionLogEntry {

        // ----------------------------------------- Instance Field ----------------------------------------

        private final Integer idNum;
        private final String username, tableName, actionType, actionState;
        private final Date actionDate;
        private final Time actionTime;

        // -------------------------------------------- Methods --------------------------------------------

        public ActionLogEntry(Integer idNum, String username, String tableName, Timestamp actionDateTime, String actionType, Integer actionState) {
            this.idNum = idNum;
            this.username = username;
            this.tableName = tableName;
            this.actionType = actionType;
            this.actionState = actionState == 1 ? "Success" : "Failure";

            String dateTime = String.valueOf(actionDateTime).substring(0, 19);
            String[] parts = dateTime.split(" ");
            actionDate = Date.valueOf(parts[0]);
            actionTime = Time.valueOf(parts[1]);
        }

        // ----------------------------------------- Getter Methods ----------------------------------------

        public Integer getIdNum() {
            return idNum;
        }

        public String getUsername() {
            return username;
        }

        public String getTableName() {
            return tableName;
        }

        public String getActionType() {
            return actionType;
        }

        public Date getActionDate() {
            return actionDate;
        }

        public Time getActionTime() {
            return actionTime;
        }

        public String getActionState() {
            return actionState;
        }

    }

    // ----------------------------------------- Instance Field ----------------------------------------

    private final List<ActionLogEntry> actionLog = new ArrayList<>();

    // -------------------------------------------- Methods --------------------------------------------

    public void addNewEntry(ActionLogEntry actionLogEntry) {
        actionLog.add(actionLogEntry);
    }

    // ----------------------------------------- Getter Methods ----------------------------------------

    public List<ActionLogEntry> getActionLog() {
        return actionLog;
    }

}
