package router.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/*
    > use test
    OR
    > use drains
    > db.sequence.insert({_id: "hosting",seq: 0})
    > db.sequence.find()
    { "_id" : "hosting", "seq" : 0 }
*/

@Document(collection = "sequence")
public class SequenceId {

    @Id
    private String id;

    private long seq;

    //get, set, toString...
    public long getSeq(){
        return seq;
    }

    public void setSeq(long seq){
        this.seq = seq;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }
}