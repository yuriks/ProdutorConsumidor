package net.yuriks;

// Basic data structure reimplementation! Wee!
public class Stack {
	private static final int STACK_SIZE = 4 * 1024;

	private int[] stack;
	private int stack_top;

	public Stack() {
		stack = new int[STACK_SIZE];
		stack_top = 0;
	}

	public synchronized int pop() {
		if (stack_top == 0) {
			// Empty stack, return default value
			return 0;
		} else {
			int value = stack[--stack_top];
			notify();
			return value;
		}
	}

	public synchronized void push(int val) throws InterruptedException {
		// If the stack is full, wait until someone pops something off it
		// before continuing.
		while (stack_top == STACK_SIZE) {
			wait();
		}
		stack[stack_top++] = val;
	}
}
