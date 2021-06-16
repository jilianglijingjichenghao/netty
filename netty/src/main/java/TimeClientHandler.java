
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




public class TimeClientHandler extends ChannelHandlerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(TimeClientHandler.class);
    private final ByteBuf firstMessage;


    public TimeClientHandler() {
        byte[] bytes = "QUERY TIME ORDER".getBytes();
        firstMessage = Unpooled.buffer(bytes.length);
        firstMessage.writeBytes(bytes);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(firstMessage);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf msg1 = (ByteBuf) msg;
        byte[] bytes = new byte[msg1.readableBytes()];
        msg1.readBytes(bytes);
        String s = new String(bytes, "UTF-8");
        System.out.println("Now is:"+s);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        LOGGER.warn("Unexcepted exception from downstream:"+cause.getMessage());
        ctx.close();
    }
}
