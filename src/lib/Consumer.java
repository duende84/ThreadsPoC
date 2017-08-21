package lib;

/**
 *
 * @author duende84
 */
public class Consumer extends Thread {

    private final SynchronizedBuffer buffer;
    private final int id;
    private final String token;

    public Consumer(SynchronizedBuffer buffer, int id, String token) {
        super("Consumer_"+id);
        this.buffer = buffer;
        this.id = id;
        this.token = token;
    }

    @Override
    public void run() {
        try {
            while(true){
                System.out.println(getName());
                Service.getGitHubRepos(buffer.getData(), this.token);
                Thread.sleep((int) (Math.random() * 5001));
            }
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex);
        }
        System.out.println("End " + getName() + "."+"\n");
    }
}
