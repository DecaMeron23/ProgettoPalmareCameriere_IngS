package classi.dataBase;

import org.jooq.codegen.GenerationTool;
import org.jooq.meta.jaxb.Configuration;
import org.jooq.meta.jaxb.Database;
import org.jooq.meta.jaxb.Generator;
import org.jooq.meta.jaxb.Jdbc;
import org.jooq.meta.jaxb.Target;

import mainPackage.CreateDB;


class GenerateCode { // NO_UCD (unused code)
	public static void main(String[] args) throws Exception {
		Jdbc jdbc = new Jdbc().withDriver("org.sqlite.JDBC").withUrl(CreateDB.DB_URL);
		Database database = new Database().withName("org.jooq.meta.sqlite.SQLiteDatabase")
				.withIncludes(".*").withExcludes("");
		Target target = new Target().withPackageName("model.generated").withDirectory("src-generated");
		Generator generator = new Generator().withDatabase(database).withTarget(target);
		
		Configuration configuration = new Configuration().withGenerator(generator).withJdbc(jdbc);
		GenerationTool.generate(configuration);
	}

}
