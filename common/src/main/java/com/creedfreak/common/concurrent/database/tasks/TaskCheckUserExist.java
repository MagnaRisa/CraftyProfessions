package com.creedfreak.common.concurrent.database.tasks;

import com.creedfreak.common.database.connection.Database;
import com.creedfreak.common.database.connection.SQLite_Conn;
import com.creedfreak.common.database.queries.QueryLib;
import com.creedfreak.common.utility.Logger;
import com.creedfreak.common.utility.UuidUtil;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class TaskCheckUserExist extends DBTask {

	private static final String PREFIX = "-CheckUserExist";
	private final UUID mUserID;
	private final String mUsername;

	public TaskCheckUserExist (UUID playerID, String username) {
		super (DatabaseTaskType.Check);
		mUserID = playerID;
		mUsername = username;
	}

	// DEBUG: Test this class to make sure it works.
	public void run () {
		String subSystemPrefix = Thread.currentThread ().getName () + PREFIX;
		DBTask postConditionTask;
		// Connection connection = mDataPool.dbConnect();
		//PreparedStatement prepStmt = null;
		//ResultSet resultSet = null;

		try (Connection conn = mDataPool.dbConnect();
			PreparedStatement prepStmt = conn.prepareStatement(QueryLib.checkUserExist)) {

			prepStmt.setBytes(1,UuidUtil.toBytes(mUserID));
			ResultSet resultSet = prepStmt.executeQuery();

			Logger.Instance().Debug(subSystemPrefix + PREFIX, "RESULT SET IS CLOSED: " + resultSet.isClosed());

			/** THE RESULT SET IS CLOSED SO I CAN'T USE resultSet.getLong or getString in this Scenario */
			boolean next = resultSet.next();
			Logger.Instance().Debug("Result Set Has Next: " + next);
			if (next) {
				Logger.Instance().Debug(subSystemPrefix + PREFIX, "Will execute TaskLoadPlayer Next");
				// TODO: What happens if this fails? How do we queue it up again?
				postConditionTask = new TaskLoadPlayer (mUserID, resultSet.getLong ("UserID"));
			} else {
				Logger.Instance().Debug(subSystemPrefix + PREFIX, "Will execute TaskSavePlayer Next");
				// TODO: Where is the player initially loaded into the PlayerManager?
				postConditionTask = new TaskSavePlayer (mUserID, mUsername);
			}

			// Attach devices to the new task.
			this.queuePostConditionTask (this.attachDevicesToTask(postConditionTask));
		}
		catch (SQLException except) {
			Logger.Instance ().Error (subSystemPrefix, "Error occurred while executing TaskCheckUserExist! "
				+ "Message: " + except.getMessage ());
		}
		finally {
			// Database.CloseResources (resultSet, prepStmt);
			//Database.CloseConnection (conn);
			Logger.Instance().Debug("Made it to the finally block");
		}
	}
}
