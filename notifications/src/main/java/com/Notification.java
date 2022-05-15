package com;
import java.sql.*;
public class Notification
{
private Connection connect()
{
Connection con = null;
try
{
Class.forName("com.mysql.jdbc.Driver");
con =
DriverManager.getConnection(
"jdbc:mysql://127.0.0.1:3306/egrid_front", "root", "");
}
catch (Exception e)
{
e.printStackTrace();
}
return con;
}
public String readNotifications()
{
String output = "";
try
{
Connection con = connect();
if (con == null)
{
return "Error while connecting to the database for reading.";
}
// Prepare the html table to be displayed
output = "<table border='1'><tr><th>Header</th>"+ "<th>Description</th>"+"<th>Update</th><th>Remove</th></tr>";
String query = "select * from notifications";
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(query);
// iterate through the rows in the result set
while (rs.next())
{
String notificationID = Integer.toString(rs.getInt("notificationID"));
String header = rs.getString("header");
String description = rs.getString("description");

// Add into the html table

output += "<tr><td><input id='hidNotificationIDUpdate'name='hidNotificationIDUpdate'type='hidden' value='" + notificationID
+ "'>" + header + "</td>";
output += "<td>" + description+ "</td>";

// buttons
output += "<td><input name='btnUpdate'type='button' value='Update'class='btnUpdate btn btn-secondary'></td>"+ "<td><input name='btnRemove'type='button' value='Remove'class='btnRemove btn btn-danger'data-notificationid='"
+ notificationID + "'>" + "</td></tr>";
}
con.close();
// Complete the html table
output += "</table>";
}
catch (Exception e)
{
output = "Error while reading the notifications.";
System.err.println(e.getMessage());
}
return output;
}




public String insertNotification(String header, String description)
{
String output = "";
try
{
Connection con = connect();
if (con == null)
{
return "Error while connecting to the database for inserting.";
}
// create a prepared statement
String query = " insert into notifications(`notificationID`,`header`,`description`) + values (?, ?, ?)";
PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
preparedStmt.setInt(1, 0);
preparedStmt.setString(2, header);
preparedStmt.setString(3, description);

// execute the statement
preparedStmt.execute();
con.close();
String newNotifications = readNotifications();
output = "{\"status\":\"success\", \"data\": \"" + newNotifications + "\"}";
}
catch (Exception e)
{
output = "{\"status\":\"error\", \"data\":\"Error while inserting the item.\"}";
System.err.println(e.getMessage());
}
return output;
}
public String updateNotification(String ID, String header, String description)
{
String output = "";
try
{
Connection con = connect();
if (con == null)
{
return "Error while connecting to the database for updating.";
}
// create a prepared statement
String query = "UPDATE notifications SET header=?,description=? WHERE notificationID=?";
PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
preparedStmt.setString(1, header);
preparedStmt.setString(2, description);

//execute the statement
preparedStmt.execute();
con.close();
String newNotifications = readNotifications();
output = "{\"status\":\"success\", \"data\": \"" + newNotifications + "\"}";
}
catch (Exception e)
{
output = "{\"status\":\"error\", \"data\":\"Error while updating the notification.\"}";
System.err.println(e.getMessage());
}
return output;
}
public String deleteNotification(String notificationID)
{
String output = "";
try
{
Connection con = connect();
if (con == null)
{
return "Error while connecting to the database for deleting.";
}
//create a prepared statement
String query = "delete from notifications where notificationID=?";
PreparedStatement preparedStmt = con.prepareStatement(query);
//binding values
preparedStmt.setInt(1, Integer.parseInt(notificationID));
//execute the statement
preparedStmt.execute();
con.close();
String newNotifications = readNotifications();
output = "{\"status\":\"success\", \"data\": \"" + newNotifications + "\"}";
}
catch (Exception e)
{
output = "{\"status\":\"error\", \"data\":\"Error while deleting the notification.\"}";
System.err.println(e.getMessage());
}
return output;
}
}
