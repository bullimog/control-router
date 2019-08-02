package router.connectors;


import router.SequenceException;

public interface SequenceDao {

    long getNextSequenceId(String key) throws SequenceException;

}
