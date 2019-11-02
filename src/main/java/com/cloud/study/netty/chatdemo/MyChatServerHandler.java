package com.cloud.study.netty.chatdemo;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * Created by Hw on 2019/10/26 15:12
 */
public class MyChatServerHandler extends SimpleChannelInboundHandler<String> {

    private static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) {
        Channel channel = ctx.channel();
        for (Channel ch : channels) {
            if (ch.equals(channel)) {
                ch.writeAndFlush("【自己】发出的信息：" + msg + "\r\n");
            } else {
                ch.writeAndFlush("【客户端】" + channel.remoteAddress() + "发出的信息：" + msg + "\r\n");
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        channels.writeAndFlush("【客户端】" + channel.remoteAddress() + "已加入聊天室\r\n");
        channels.add(channel);
        System.out.println("客户端的数量为：" + channels.size());
        System.out.println("【客户端】" + channel.remoteAddress() + "已上线");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        channels.writeAndFlush("【客户端】" + channel.remoteAddress() + "已离开聊天室\r\n");
    }
}
