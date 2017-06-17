package magnarisa.craftyprofessions.utility;

import java.nio.ByteBuffer;
import java.util.UUID;

/**
 * This class contains the Utilities for a UUID or Unique Identifier
 */
public class UuidUtil
{
    public static byte[] toBytes (UUID uuid)
    {
        ByteBuffer byteBuffer = ByteBuffer.wrap (new byte [16]);
        byteBuffer.putLong (uuid.getMostSignificantBits ());
        byteBuffer.putLong (uuid.getLeastSignificantBits ());
        return byteBuffer.array ();
    }

    public static UUID fromBytes (byte[] bytes)
    {
        ByteBuffer byteBuffer = ByteBuffer.wrap (bytes);
        long mostSigLong = byteBuffer.getLong ();
        long leastSigLong = byteBuffer.getLong ();
        return new UUID (mostSigLong, leastSigLong);
    }
}
