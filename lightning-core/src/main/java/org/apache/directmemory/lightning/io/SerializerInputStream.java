package org.apache.directmemory.lightning.io;

import java.io.IOException;
import java.io.ObjectInput;

import org.apache.directmemory.lightning.Serializer;
import org.apache.directmemory.lightning.Source;

public class SerializerInputStream
    implements ObjectInput
{

    private final Serializer serializer;

    private final Source source;

    public SerializerInputStream( Serializer serializer, Source source )
    {
        this.serializer = serializer;
        this.source = source;
    }

    @Override
    public void readFully( byte[] b )
        throws IOException
    {
        readFully( b, 0, b.length );
    }

    @Override
    public void readFully( byte[] b, int off, int len )
        throws IOException
    {
        source.readBytes( b, off, len );
    }

    @Override
    public int skipBytes( int n )
        throws IOException
    {
        throw new UnsupportedOperationException( "skipBytes is not implemented in the underlying Source type" );
    }

    @Override
    public boolean readBoolean()
        throws IOException
    {
        return source.readBoolean();
    }

    @Override
    public byte readByte()
        throws IOException
    {
        return source.readByte();
    }

    @Override
    public int readUnsignedByte()
        throws IOException
    {
        return source.readUnsignedByte();
    }

    @Override
    public short readShort()
        throws IOException
    {
        return source.readShort();
    }

    @Override
    public int readUnsignedShort()
        throws IOException
    {
        return (char) ( ( ( source.readByte() & 0x000000FF ) << 8 ) | ( source.readByte() & 0x000000FF ) );
    }

    @Override
    public char readChar()
        throws IOException
    {
        return source.readChar();
    }

    @Override
    public int readInt()
        throws IOException
    {
        return source.readInt();
    }

    @Override
    public long readLong()
        throws IOException
    {
        return source.readLong();
    }

    @Override
    public float readFloat()
        throws IOException
    {
        return source.readFloat();
    }

    @Override
    public double readDouble()
        throws IOException
    {
        return source.readDouble();
    }

    @Override
    public String readLine()
        throws IOException
    {
        return readUTF();
    }

    @Override
    public String readUTF()
        throws IOException
    {
        return source.readString();
    }

    @Override
    public Object readObject()
        throws ClassNotFoundException, IOException
    {
        return serializer.deserialize( source );
    }

    @Override
    public int read()
        throws IOException
    {
        return 0;
    }

    @Override
    public int read( byte[] b )
        throws IOException
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int read( byte[] b, int off, int len )
        throws IOException
    {
        return source.readByte();
    }

    @Override
    public long skip( long n )
        throws IOException
    {
        throw new UnsupportedOperationException( "skip is not implemented in the underlying Source type" );
    }

    @Override
    public int available()
        throws IOException
    {
        return (int) source.readableBytes();
    }

    @Override
    public void close()
        throws IOException
    {
        // nothing to implement here
    }

}
