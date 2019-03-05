package router.connectors;

import java.io.IOException;
import java.sql.Timestamp;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;


public class MQTTConnectorImpl implements MQTTConnector{

    // Private instance variables
    private MqttClient client;
    private String brokerUrl;
    private MqttConnectOptions conOpt;
    private boolean clean;

    /**
     * Constructs an instance of the sample client wrapper
     *
     * @param brokerUrl    the url of the server to connect to
     * @param clientId     the client id to connect with
     * @param cleanSession clear state at end of connection or not (durable or non-durable subscriptions)
     * @throws MqttException
     */
    public MQTTConnectorImpl(String brokerUrl, String clientId, boolean cleanSession) throws MqttException {
        this.brokerUrl = brokerUrl;
        this.clean = cleanSession;

        String tmpDir = System.getProperty("java.io.tmpdir");
        MqttDefaultFilePersistence dataStore = new MqttDefaultFilePersistence(tmpDir);

        conOpt = new MqttConnectOptions();
        conOpt.setCleanSession(clean);

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
        client.connect(conOpt);
        log("Connected to " + brokerUrl + " with client ID " + client.getClientId());

        log("Subscribing to topic \"" + topicName + "\" qos " + qos);
        client.subscribe(topicName, qos);
    }


    public void unsubscribe(String topicName) throws MqttException {
        client.unsubscribe(topicName);
        client.disconnect();
        client.close();
        log("Disconnected");
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
        //System.exit(1);
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


