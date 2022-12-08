import java.lang.reflect.*;

/**
 * 对Type进行封装
 * @param <T>
 */
public class TypeLiteral<T> {
    private Type type;

    /**
     * 这个构造器可以被匿名子类调用
     * 比如new TypeLiteral<xx>(){}
     */
    public TypeLiteral()
    {
        Type parentType = getClass().getGenericSuperclass();
        if(parentType instanceof ParameterizedType paramType)
        {
            type = paramType.getActualTypeArguments()[0];
        }
        else
            throw new UnsupportedOperationException("Construct as new TypeLiteral<...>(){}");
    }

    public TypeLiteral(Type type)
    {
        this.type = type;
    }

    public static TypeLiteral<?> of(Type type)
    {
        return new TypeLiteral<Object>(type);
    }

    public boolean equals(Object otherObject)
    {
        return otherObject instanceof TypeLiteral otherLiteral
                && type.equals(otherLiteral.type);
    }

    public String toString()
    {
        if(type instanceof Class clazz) return clazz.getName();
        else return type.toString();
    }

    public int hashCode()
    {
        return type.hashCode();
    }
}
