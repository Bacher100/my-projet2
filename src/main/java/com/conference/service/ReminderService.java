import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ReminderService {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public void scheduleReminder(Runnable reminderTask, long delayInMinutes) {
        scheduler.schedule(reminderTask, delayInMinutes, TimeUnit.MINUTES);
    }

    public void shutdown() {
        scheduler.shutdown();
    }
}