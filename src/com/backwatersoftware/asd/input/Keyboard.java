package com.backwatersoftware.asd.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

	private boolean[] keys = new boolean[500];
	public boolean up, down, left, right, escape, enter, n1, n2, n3, n4, n5, n6, n7, n8, n9, n0;
	public boolean Q, W, E, R, T, Y, U, I, O, P, A, S, D, F, G, H, J, K, L, Z, X, C, V, B, N, M, Shift;
	public boolean WASD;
	public boolean[] num = { this.keys[KeyEvent.VK_0], this.keys[KeyEvent.VK_1], this.keys[KeyEvent.VK_2], this.keys[KeyEvent.VK_3], this.keys[KeyEvent.VK_4], this.keys[KeyEvent.VK_5], this.keys[KeyEvent.VK_6], this.keys[KeyEvent.VK_7], this.keys[KeyEvent.VK_8], this.keys[KeyEvent.VK_9] };

	public void update() {
		if (this.WASD) {
			this.up = this.keys[KeyEvent.VK_W];
			this.down = this.keys[KeyEvent.VK_S];
			this.left = this.keys[KeyEvent.VK_A];
			this.right = this.keys[KeyEvent.VK_D];
		}
		if (!this.WASD) {
			this.up = this.keys[KeyEvent.VK_UP];
			this.down = this.keys[KeyEvent.VK_DOWN];
			this.left = this.keys[KeyEvent.VK_LEFT];
			this.right = this.keys[KeyEvent.VK_RIGHT];
		}
		this.escape = this.keys[KeyEvent.VK_ESCAPE];
		this.enter = this.keys[KeyEvent.VK_ENTER];
		this.Q = this.keys[KeyEvent.VK_Q];
		this.W = this.keys[KeyEvent.VK_W];
		this.E = this.keys[KeyEvent.VK_E];
		this.R = this.keys[KeyEvent.VK_R];
		this.T = this.keys[KeyEvent.VK_T];
		this.Y = this.keys[KeyEvent.VK_Y];
		this.U = this.keys[KeyEvent.VK_U];
		this.I = this.keys[KeyEvent.VK_I];
		this.O = this.keys[KeyEvent.VK_O];
		this.P = this.keys[KeyEvent.VK_P];
		this.A = this.keys[KeyEvent.VK_A];
		this.S = this.keys[KeyEvent.VK_S];
		this.D = this.keys[KeyEvent.VK_D];
		this.F = this.keys[KeyEvent.VK_F];
		this.G = this.keys[KeyEvent.VK_G];
		this.H = this.keys[KeyEvent.VK_H];
		this.J = this.keys[KeyEvent.VK_J];
		this.K = this.keys[KeyEvent.VK_K];
		this.L = this.keys[KeyEvent.VK_L];
		this.Z = this.keys[KeyEvent.VK_Z];
		this.X = this.keys[KeyEvent.VK_X];
		this.C = this.keys[KeyEvent.VK_C];
		this.V = this.keys[KeyEvent.VK_V];
		this.B = this.keys[KeyEvent.VK_B];
		this.N = this.keys[KeyEvent.VK_N];
		this.M = this.keys[KeyEvent.VK_M];
		this.Shift = this.keys[KeyEvent.VK_SHIFT];
		for (int i = 0; i < this.num.length; i++) {
			switch (i) {
			case 0: {
				this.num[i] = this.keys[KeyEvent.VK_0];
				break;
			}
			case 1: {
				this.num[i] = this.keys[KeyEvent.VK_1];
				break;
			}
			case 2: {
				this.num[i] = this.keys[KeyEvent.VK_2];
				break;
			}
			case 3: {
				this.num[i] = this.keys[KeyEvent.VK_3];
				break;
			}
			case 4: {
				this.num[i] = this.keys[KeyEvent.VK_4];
				break;
			}
			case 5: {
				this.num[i] = this.keys[KeyEvent.VK_5];
				break;
			}
			case 6: {
				this.num[i] = this.keys[KeyEvent.VK_6];
				break;
			}
			case 7: {
				this.num[i] = this.keys[KeyEvent.VK_7];
				break;
			}
			case 8: {
				this.num[i] = this.keys[KeyEvent.VK_8];
				break;
			}
			case 9: {
				this.num[i] = this.keys[KeyEvent.VK_9];
				break;
			}
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		this.keys[e.getKeyCode()] = true;

	}

	@Override
	public void keyReleased(KeyEvent e) {
		this.keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
