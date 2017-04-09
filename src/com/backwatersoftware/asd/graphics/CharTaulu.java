package com.backwatersoftware.asd.graphics;

public class CharTaulu {

	public Sprite[] t;

	public CharTaulu(SpriteSheet sheet, SpriteSheet numers) {
		this.t = new Sprite[100];
		for (int i = 0; i < this.t.length; i++) {
			this.t[i] = new Sprite(0, 0, 0, 0, SpriteSheet.errors, 0, 16, 0);
		}
		

		lisaa(new Sprite(5, 6, 0, 0, sheet, 'Q', 6, 0));
		lisaa(new Sprite(5, 6, 1, 0, sheet, 'W', 6, 0));
		lisaa(new Sprite(5, 6, 2, 0, sheet, 'E', 5, 0));
		lisaa(new Sprite(5, 6, 3, 0, sheet, 'R', 6, 0));
		lisaa(new Sprite(5, 6, 4, 0, sheet, 'T', 6, 0));
		lisaa(new Sprite(5, 6, 5, 0, sheet, 'Y', 6, 0));
		lisaa(new Sprite(5, 6, 6, 0, sheet, 'U', 6, 0));
		lisaa(new Sprite(5, 6, 7, 0, sheet, 'I', 4, 0));
		lisaa(new Sprite(5, 6, 8, 0, sheet, 'O', 6, 0));
		lisaa(new Sprite(5, 6, 9, 0, sheet, 'P', 6, 0));
		lisaa(new Sprite(5, 6, 10, 0, sheet, 'A', 6, 0));
		lisaa(new Sprite(5, 6, 11, 0, sheet, 'S', 6, 0));
		lisaa(new Sprite(5, 6, 12, 0, sheet, 'D', 6, 0));
		lisaa(new Sprite(5, 6, 13, 0, sheet, 'F', 6, 0));
		lisaa(new Sprite(5, 6, 14, 0, sheet, 'G', 6, 0));
		lisaa(new Sprite(5, 6, 15, 0, sheet, 'H', 6, 0));
		lisaa(new Sprite(5, 6, 16, 0, sheet, 'J', 6, 0));
		lisaa(new Sprite(5, 6, 17, 0, sheet, 'K', 6, 0));
		lisaa(new Sprite(5, 6, 18, 0, sheet, 'L', 6, 0));
		lisaa(new Sprite(5, 6, 19, 0, sheet, 'Z', 6, 0));
		lisaa(new Sprite(5, 6, 20, 0, sheet, 'X', 6, 0));
		lisaa(new Sprite(5, 6, 21, 0, sheet, 'C', 6, 0));
		lisaa(new Sprite(5, 6, 22, 0, sheet, 'V', 6, 0));
		lisaa(new Sprite(5, 6, 23, 0, sheet, 'B', 6, 0));
		lisaa(new Sprite(5, 6, 24, 0, sheet, 'N', 6, 0));
		lisaa(new Sprite(5, 6, 25, 0, sheet, 'M', 6, 0));

		lisaa(new Sprite(5, 6, 0, 1, sheet, 'q', 5, 2));
		lisaa(new Sprite(5, 6, 1, 1, sheet, 'w', 6, 0));
		lisaa(new Sprite(5, 6, 2, 1, sheet, 'e', 4, 0));
		lisaa(new Sprite(5, 6, 3, 1, sheet, 'r', 3, 0));
		lisaa(new Sprite(5, 6, 4, 1, sheet, 't', 4, 0));
		lisaa(new Sprite(5, 6, 5, 1, sheet, 'y', 4, 2));
		lisaa(new Sprite(5, 6, 6, 1, sheet, 'u', 4, 0));
		lisaa(new Sprite(5, 6, 7, 1, sheet, 'i', 2, 0));
		lisaa(new Sprite(5, 6, 8, 1, sheet, 'o', 5, 0));
		lisaa(new Sprite(5, 6, 9, 1, sheet, 'p', 4, 2));
		lisaa(new Sprite(5, 6, 10, 1, sheet, 'a', 5, 0));
		lisaa(new Sprite(5, 6, 11, 1, sheet, 's', 4, 0));
		lisaa(new Sprite(5, 6, 12, 1, sheet, 'd', 5, 0));
		lisaa(new Sprite(5, 6, 13, 1, sheet, 'f', 4, 0));
		lisaa(new Sprite(5, 6, 14, 1, sheet, 'g', 4, 2));
		lisaa(new Sprite(5, 6, 15, 1, sheet, 'h', 4, 0));
		lisaa(new Sprite(5, 6, 16, 1, sheet, 'j', 3, 2));
		lisaa(new Sprite(5, 6, 17, 1, sheet, 'k', 4, 0));
		lisaa(new Sprite(5, 6, 18, 1, sheet, 'l', 2, 0));
		lisaa(new Sprite(5, 6, 19, 1, sheet, 'z', 5, 0));
		lisaa(new Sprite(5, 6, 20, 1, sheet, 'x', 4, 0));
		lisaa(new Sprite(5, 6, 21, 1, sheet, 'c', 5, 0));
		lisaa(new Sprite(5, 6, 22, 1, sheet, 'v', 5, 0));
		lisaa(new Sprite(5, 6, 23, 1, sheet, 'b', 6, 0));
		lisaa(new Sprite(5, 6, 24, 1, sheet, 'n', 4, 0));
		lisaa(new Sprite(5, 6, 25, 1, sheet, 'm', 5, 0));

		lisaa(new Sprite(5, 6, 0, 0, SpriteSheet.Symbols, '.', 3, 0));
		lisaa(new Sprite(5, 6, 1, 0, SpriteSheet.Symbols, ',', 3, 1));
		lisaa(new Sprite(5, 6, 2, 0, SpriteSheet.Symbols, '/', 5, 0));
		lisaa(new Sprite(5, 6, 3, 0, SpriteSheet.Symbols, '\\', 5, 0));
		lisaa(new Sprite(5, 6, 4, 0, SpriteSheet.Symbols, '|', 5, 0));
		lisaa(new Sprite(5, 6, 5, 0, SpriteSheet.Symbols, '?', 5, 0));
		lisaa(new Sprite(5, 6, 6, 0, SpriteSheet.Symbols, '!', 3, 0));
		lisaa(new Sprite(5, 6, 7, 0, SpriteSheet.Symbols, ':', 3, 0));

		lisaa(new Sprite(4, 6, 0, 0, numers, '0', 4, 0));
		lisaa(new Sprite(4, 6, 1, 0, numers, '1', 4, 0));
		lisaa(new Sprite(4, 6, 2, 0, numers, '2', 4, 0));
		lisaa(new Sprite(4, 6, 3, 0, numers, '3', 4, 0));
		lisaa(new Sprite(4, 6, 4, 0, numers, '4', 4, 0));
		lisaa(new Sprite(4, 6, 5, 0, numers, '5', 4, 0));
		lisaa(new Sprite(4, 6, 6, 0, numers, '6', 4, 0));
		lisaa(new Sprite(4, 6, 7, 0, numers, '7', 4, 0));
		lisaa(new Sprite(4, 6, 8, 0, numers, '8', 4, 0));
		lisaa(new Sprite(4, 6, 9, 0, numers, '9', 4, 0));
	}

	public Sprite getSprite(char c) {
		try {
			return this.t[etsi(c)];
		} catch (IndexOutOfBoundsException asd) {
			return Sprite.errorSprite;
		}
	}

	public int lisaa(Sprite alkio) {
		int collisions = 0;
		int k = alkio.key;
		for (int i = 0; i < this.t.length; i++) {
			int j = hajautus(k, i);
			if (this.t[j].key == 0 || this.t[j].key == -1) {
				this.t[j] = alkio;
				System.out.println("Sprite lisäys onnistui");
				return collisions;

			}
			collisions++;
		}
		return -1;
	}

	public int etsi(int avain) {
		int k = avain;
		for (int i = 0; i < this.t.length; i++) {
			int j = hajautus(k, i);
			if (this.t[j].key == 0) {
				break;
			}
			if (this.t[j].key != -1 && this.t[j].key == k) {
				return j;
			}
		}
		return -1;
	}

	public void poista(int alkio) {
		int j = etsi(alkio);
		if (j < 0) {
			System.out.println("NYT MENI PIELEE EI TEHÄ MITTÄÄ");
			return;
		}
		this.t[j].key = -1;
	}

	public int hajautus(int k, int i) {
		return ((k % this.t.length) + i) % this.t.length;
	}

	@SuppressWarnings("boxing")
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < this.t.length; i++) {
			if (this.t[i].key == 0 || this.t[i].key == -1) {
				sb.append(" - ");
			} else {
				sb.append(String.format("%3d", this.t[i]));
			}
		}
		return sb.toString();
	}

}
