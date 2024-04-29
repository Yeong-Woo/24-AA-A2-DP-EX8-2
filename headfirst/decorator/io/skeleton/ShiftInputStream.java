package headfirst.decorator.io.skeleton;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ShiftInputStream extends FilterInputStream {
	int offset;

	// Decorator pattern should call the constructor of superclass.
	public ShiftInputStream(InputStream inputStream) {
		super(inputStream);
		this.offset = 0;
	}

	public ShiftInputStream(InputStream inputStream, int offset) {
		super(inputStream);
		this.offset = offset;
	}

	// calls the superclass method and perform additional tasks.
	@Override
	public int read() throws IOException {
		int c = super.read();
		return (c == -1) ? c : shift(c);
	}

	@Override
	public int read(byte[] b, int offset, int len) throws IOException {
		int result = super.read(b, offset, len);
		for (int i = offset; i < offset + result; i++)
			b[i] = (byte) shift(b[i]);

		return result;
	}

	private char shift(int c) {
		if (65 <= c && c <= 90) {
			c += offset;
			if (c < 65) {
				c += 26;
			} else if (c > 90) {
				c -= 26;
			}
		} else if (97 <= c && c <= 122) {
			c += offset;
			if (c < 97) {
				c += 26;
			} else if (c > 122) {
				c -= 26;
			}
		}

		return (char) c;
	}
}
