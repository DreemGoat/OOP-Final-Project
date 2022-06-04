package source;

import java.sql.Connection;
import java.sql.SQLException;

interface Shop { //Shop interface that will be polymorphed in the ShopService class
	public People Login(Connection connection)throws SQLException;
	public void Register(Connection connection)throws SQLException;
	public void Buy (Connection connection)throws SQLException;
	public void input(Connection connection)throws SQLException;
}