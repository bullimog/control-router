package router.connectors;

import java.sql.Timestamp;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;


public class MqttConnectionImpl implements MqttConnection {

    private MqttClient client;
    private String brokerUrl;
    private MqttConnectOptions conOpt;

    /**
     * Configures an instance of the sample client wrapper
     *
     * @param brokerUrl    the url of the server to connect to
     * @param clientId     the client id to connect with
     * @param cleanSession clear state at end of connection or not (durable or non-durable subscriptions)
     * @throws MqttException
     */
    public void openConnection(String brokerUrl, String clientId, boolean cleanSession) throws MqttException{
        this.brokerUrl = brokerUrl;

        String tmpDir = System.getProperty("java.io.tmpdir");
        MqttDefaultFilePersistence dataStore = new MqttDefaultFilePersistence(tmpDir);

        conOpt = new MqttConnectOptions();
        conOpt.setCleanSession(cleanSession);

        client = new MqttClient(this.brokerUrl, clientId, dataStore);
        client.setCallback(this);
    }


    /**
     * Subscribe to a topic on an MQTT server
     * Once subscribed this method waits for the messages to arrive from the server
     * that match the subscription. It continues listening for messages until the enter key is
     * pressed.
     *
     * @param topicName to subscribe to (can be wild carded)
     * @param qos       the maximum quality of service to receive messages at for this subscription
     * @throws MqttException
     */
    public void subscribe(String topicName, int qos) throws MqttException {
        if( (client != null) && (conOpt != null)) {
            client.connect(conOpt);
            log("Connected to " + brokerUrl + " with client ID " + client.getClientId());

            log("Subscribing to topic \"" + topicName + "\" qos " + qos);
            client.subscribe(topicName, qos);
        }
        else{
            Throwable t = new Throwable("MQTT Connection not configured. Has openConnection() been executed?");
            throw (new MqttException(t));
        }
    }


    public void unsubscribe(String topicName) throws MqttException {
        if( client != null ) {
            client.unsubscribe(topicName);
            client.disconnect();
            client.close();
            log("Disconnected");
        }
        else{
            Throwable t = new Throwable("MQTT Connection not configured. Has openConnection() been executed?");
            throw (new MqttException(t));
        }
    }



    /**
     * Utility method to handle logging. If 'quietMode' is set, this method does nothing
     *
     * @param message the message to log
     */
    private void log(String message) {
        System.out.println(message);
    }

    /****************************************************************/
    /* Methods to implement the MqttCallback interface              */
    /****************************************************************/

    /**
     * @see MqttCallback#connectionLost(Throwable)
     */
    public void connectionLost(Throwable cause) {
        log("Connection to " + brokerUrl + " lost!" + cause);
    }


    /**
     * @see MqttCallback#deliveryComplete(IMqttDeliveryToken)
     */
    public void deliveryComplete(IMqttDeliveryToken token) {
        log("Message: " + token + " delivered to " + brokerUrl);
    }

    /**
     * @see MqttCallback#messageArrived(String, MqttMessage)
     */
    public void messageArrived(String topic, MqttMessage message) throws MqttException {

        String time = new Timestamp(System.currentTimeMillis()).toString();
        System.out.println("Time:\t" + time +
                "  Topic:\t" + topic +
                "  Message:\t" + new String(message.getPayload()) +
                "  QoS:\t" + message.getQos());
    }

    /****************************************************************/
    /* End of MqttCallback methods                                  */
    /****************************************************************/

}


