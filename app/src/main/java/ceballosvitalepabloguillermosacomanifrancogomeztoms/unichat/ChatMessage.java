package ceballosvitalepabloguillermosacomanifrancogomeztoms.unichat;

/**
 * Created by Lark Digital on 24/2/2018.
 */

/**
 * Clase mensaje de Chat que guardará los mensajes enviados apra ser usados por el adaptador
 */
class ChatMessage {

    private boolean ladoIzquierdo;
    private String mensaje;

    public ChatMessage(boolean left, String msg){
        super(); //NO se que ha que hace super(), pero por ahora se hace así
        ladoIzquierdo = left;
        mensaje = msg;
    }

    public boolean getLado(){
        return ladoIzquierdo;
    }

    public String getMensaje(){
        return mensaje;
    }

}
