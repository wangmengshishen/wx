package com.wm.project.common.redis.cluster;

import java.io.Closeable;

public abstract class SerializeTranscoder {

	public abstract byte[] serialize(Object value);

	public abstract Object deserialize(byte[] in);

	public void close(Closeable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (Exception e) {
				System.out.println("Unable to close " + closeable);
			}
		}
	}
}
