package orb.__mantle.algorithms;

/*Chinchbug
 *  mrl.nyu.edu/~perlin/paper445.pdf
 http://www.openprocessing.org/sketch/58104
 */


public enum M_Noise {

	INSTANCE;

	private static int p[];

	M_Noise() {
		setupPermutationTable();
	}

	// . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

	private void setupPermutationTable() {
		int permutation[] = { 151, 160, 137, 91, 90, 15, 131, 13, 201, 95, 96, 53, 194, 233, 7, 225, 140, 36, 103, 30, 69, 142, 8, 99, 37, 240, 21, 10, 23, 190, 6, 148, 247, 120, 234, 75, 0, 26, 197, 62, 94, 252, 219, 203, 117, 35, 11, 32, 57, 177, 33, 88, 237, 149, 56, 87, 174, 20, 125, 136, 171, 168, 68, 175, 74, 165, 71, 134, 139, 48, 27, 166, 77, 146, 158, 231, 83, 111, 229, 122, 60, 211, 133, 230, 220, 105, 92, 41, 55, 46, 245, 40, 244, 102, 143, 54, 65, 25, 63, 161, 1, 216, 80, 73, 209, 76, 132, 187, 208, 89, 18, 169, 200, 196, 135, 130, 116, 188, 159, 86, 164, 100, 109, 198, 173, 186, 3, 64, 52, 217, 226, 250, 124, 123, 5, 202, 38, 147, 118, 126, 255, 82, 85, 212, 207, 206, 59, 227, 47, 16, 58, 17, 182, 189, 28, 42, 223, 183, 170, 213, 119, 248, 152, 2, 44, 154, 163, 70, 221, 153, 101, 155, 167, 43, 172, 9, 129, 22, 39, 253, 19, 98, 108, 110, 79, 113, 224, 232, 178, 185, 112, 104, 218, 246, 97, 228, 251, 34, 242, 193, 238, 210, 144, 12, 191, 179, 162, 241, 81, 51, 145, 235, 249, 14, 239, 107, 49, 192, 214, 31, 181, 199, 106, 157, 184, 84, 204, 176, 115, 121, 50, 45, 127, 4, 150, 254, 138, 236, 205, 93, 222, 114, 67, 29, 24, 72, 243, 141, 128, 195, 78, 66, 215, 61, 156, 180 };

		p = new int[512];
		for (int i = 0; i < 256; i++) {
			p[i] = p[i + 256] = permutation[i];
		}
	}

	// . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

	public static M_Noise getInstance() {
		return INSTANCE;
	}

	// . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
	
	/**
	 *  returns value between -1 and 1
	 */
	public static float noise(float x) {
		return noise(x, 0f, 0f);
	}
	
	// . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

	/**
	 *  returns value between -1 and 1
	 */
	public static float noise(float x, float y) {
		return noise(x, y, 0f);
	}
	
	// . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

	/**
	 *  returns value between -1 and 1
	 */
	public static float noise(float x, float y, float z) {
		int X = (int)Math.floor(x) & 255;
		int Y = (int)Math.floor(y) & 255;
		int Z = (int)Math.floor(z) & 255;
		x -= (int)Math.floor(x);
		y -= (int)Math.floor(y);
		z -= (int)Math.floor(z);
		float u = fade(x);
		float v = fade(y);
		float w = fade(z);
		int A = p[X] + Y;
		int AA = p[A] + Z;
		int AB = p[A + 1] + Z;
		int B = p[X + 1] + Y;
		int BA = p[B] + Z;
		int BB = p[B + 1] + Z;

		return lerp2(w, lerp2(v, lerp2(u, grad(p[AA], x, y, z), grad(p[BA], x - 1, y, z)), lerp2(u, grad(p[AB], x, y - 1, z), grad(p[BB], x - 1, y - 1, z))), lerp2(v, lerp2(u, grad(p[AA + 1], x, y, z - 1), grad(p[BA + 1], x - 1, y, z - 1)), lerp2(u, grad(p[AB + 1], x, y - 1, z - 1), grad(p[BB + 1], x - 1, y - 1, z - 1))));
	}

	// . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

	private static float lerp2(float t, float a, float b) {
		return (b - a) * t + a;
	}

	// . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

	private static float grad(int hash, float x, float y, float z) {
		int h = hash & 15;
		float u = h < 8 ? x : y;
		float v = h < 4 ? y : h == 12 || h == 14 ? x : z;
		return ((h & 1) == 0 ? u : -u) + ((h & 2) == 0 ? v : -v);
	}

	// . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

	private static float fade(float t) {
		return ((t * 6 - 15) * t + 10) * t * t * t;
	}

	// . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
}
