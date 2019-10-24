package com.cloud.study.netty.seconddemo;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

/**
 * Created by Hw on 2019/10/24 21:03
 */
public class MyServerHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) {
        System.out.println(ctx.channel().remoteAddress() + " to server:" + msg);
        ctx.channel().writeAndFlush("From server: " + UUID.randomUUID());
    }
}
