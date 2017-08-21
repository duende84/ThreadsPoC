package lib;

/**
 *
 * @author duende84
 */
public class SynchronizedBuffer {

    private String data;

    public SynchronizedBuffer() {
        this.data = null;
    }

    public synchronized void setData(String value) {
        String name = Thread.currentThread().getName();
        while (data != null) {
            try {
                System.out.println(name + " tries write.");
                showStatus("Buffer full. " + name + " waiting.");
                wait();
            } catch (InterruptedException ex) {
                System.out.println("ERROR: " + ex);
            }
        }
        data = value;
        showStatus(name + " write: " + data);
        notifyAll();
    }

    public synchronized String getData() {
        String name = Thread.currentThread().getName();
        while (data == null) {
            try {
                System.out.println(name + " tries read.");
                showStatus("Buffer empty. " + name + " waiting.");
                wait();
            } catch (InterruptedException ex) {
                System.out.println("ERROR: " + ex);
            }
        }
        showStatus(name +" read: " + data);
        String copy = data;
        data = null;
        notify();
        return copy;
    }

    public void showStatus(String operacion) {
        System.out.println("Operation: " + operacion+"\n");
    }
}
