package com.example.assignment.model;

import java.util.concurrent.CountDownLatch;

public class TaskControl
{

    public volatile TaskStatus status;

    public volatile Thread runningThread;

    public CountDownLatch latch = new CountDownLatch(1);
}
