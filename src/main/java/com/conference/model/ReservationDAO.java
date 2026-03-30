import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {
    private Connection connection;

    public ReservationDAO(Connection connection) {
        this.connection = connection;
    }

    public void save(Reservation reservation) throws SQLException {
        String sql = "INSERT INTO reservations (user_id, room_id, start_time, end_time, status) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, reservation.getUserId());
            stmt.setInt(2, reservation.getRoomId());
            stmt.setTimestamp(3, reservation.getStartTime());
            stmt.setTimestamp(4, reservation.getEndTime());
            stmt.setString(5, reservation.getStatus());
            stmt.executeUpdate();
        }
    }

    public Reservation findById(int id) throws SQLException {
        String sql = "SELECT * FROM reservations WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Reservation(
                    rs.getInt("id"),
                    rs.getInt("user_id"),
                    rs.getInt("room_id"),
                    rs.getTimestamp("start_time"),
                    rs.getTimestamp("end_time"),
                    rs.getString("status")
                );
            }
        }
        return null;
    }

    public List<Reservation> findByUserId(int userId) throws SQLException {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservations WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                reservations.add(new Reservation(
                    rs.getInt("id"),
                    rs.getInt("user_id"),
                    rs.getInt("room_id"),
                    rs.getTimestamp("start_time"),
                    rs.getTimestamp("end_time"),
                    rs.getString("status")
                ));
            }
        }
        return reservations;
    }

    public List<Reservation> findUpcoming() throws SQLException {
        List<Reservation> upcomingReservations = new ArrayList<>();
        String sql = "SELECT * FROM reservations WHERE start_time > CURRENT_TIMESTAMP";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                upcomingReservations.add(new Reservation(
                    rs.getInt("id"),
                    rs.getInt("user_id"),
                    rs.getInt("room_id"),
                    rs.getTimestamp("start_time"),
                    rs.getTimestamp("end_time"),
                    rs.getString("status")
                ));
            }
        }
        return upcomingReservations;
    }

    public boolean isRoomAvailable(int roomId, java.sql.Timestamp startTime, java.sql.Timestamp endTime) throws SQLException {
        String sql = "SELECT COUNT(*) FROM reservations WHERE room_id = ? AND ((start_time <= ? AND end_time >= ?) OR (start_time >= ? AND end_time <= ?))";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, roomId);
            stmt.setTimestamp(2, endTime);
            stmt.setTimestamp(3, startTime);
            stmt.setTimestamp(4, startTime);
            stmt.setTimestamp(5, endTime);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) == 0;
            }
        }
        return false;
    }

    public void update(Reservation reservation) throws SQLException {
        String sql = "UPDATE reservations SET user_id = ?, room_id = ?, start_time = ?, end_time = ?, status = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, reservation.getUserId());
            stmt.setInt(2, reservation.getRoomId());
            stmt.setTimestamp(3, reservation.getStartTime());
            stmt.setTimestamp(4, reservation.getEndTime());
            stmt.setString(5, reservation.getStatus());
            stmt.setInt(6, reservation.getId());
            stmt.executeUpdate();
        }
    }

    public void updateStatut(int id, String status) throws SQLException {
        String sql = "UPDATE reservations SET status = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, status);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
    }
}