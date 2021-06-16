import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;

public class TimeServerHeader extends ChannelHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf msg1 = (ByteBuf) msg;
        byte[] bytes = new byte[msg1.readableBytes()];
        msg1.readBytes(bytes);
        String s = new String(bytes, "UTF-8");
        System.out.println("The time server receive order:"+s);
        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(s)?new Date(System.currentTimeMillis()).toString():"BAD ORDER";
        ByteBuf byteBuf = Unpooled.copiedBuffer(currentTime.getBytes());
        ctx.write(byteBuf);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
