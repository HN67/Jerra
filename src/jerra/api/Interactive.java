package jerra.api;

/**
 * Interactive
 */
public interface Interactive {

    /**
     * Queues a String event to be handled next update
     * @param command a String, will be handled next update
     */
    public void queue(String command);

    /**
     * Clears the queue
     */
    public void clearQueue();
    
}