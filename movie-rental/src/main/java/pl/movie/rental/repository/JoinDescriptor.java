package pl.movie.rental.repository;

import com.mysema.query.JoinType;
import com.mysema.query.types.path.ListPath;
import com.mysema.query.types.path.SetPath;

public class JoinDescriptor {
	public final ListPath path;
	public final JoinType type;

	public JoinDescriptor(ListPath path, JoinType type) {
		this.path = path;
		this.type = type;
	}

	public static JoinDescriptor join(ListPath path) {
		return new JoinDescriptor(path, JoinType.JOIN);
	}

	public static JoinDescriptor innerJoin(ListPath path) {
		return new JoinDescriptor(path, JoinType.INNERJOIN);
	}

	public static JoinDescriptor leftJoin(ListPath path) {
		return new JoinDescriptor(path, JoinType.LEFTJOIN);
	}

	public static JoinDescriptor rightJoin(ListPath path) {
		return new JoinDescriptor(path, JoinType.RIGHTJOIN);
	}

	public static JoinDescriptor fullJoin(ListPath path) {
		return new JoinDescriptor(path, JoinType.FULLJOIN);
	}

}
