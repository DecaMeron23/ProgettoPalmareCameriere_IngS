/*
 * This file is generated by jOOQ.
 */
package model.generated;


import java.util.Arrays;
import java.util.List;

import model.generated.tables.ComponenteTables;
import model.generated.tables.CopertoTables;
import model.generated.tables.OrdineTables;
import model.generated.tables.PiattoTables;
import model.generated.tables.PiattoOrdinatoTables;
import model.generated.tables.ResocontoTavoloTables;
import model.generated.tables.TavoloTables;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DefaultSchema extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>DEFAULT_SCHEMA</code>
     */
    public static final DefaultSchema DEFAULT_SCHEMA = new DefaultSchema();

    /**
     * The table <code>COMPONENTE</code>.
     */
    public final ComponenteTables COMPONENTE = ComponenteTables.COMPONENTE;

    /**
     * The table <code>COPERTO</code>.
     */
    public final CopertoTables COPERTO = CopertoTables.COPERTO;

    /**
     * The table <code>ORDINE</code>.
     */
    public final OrdineTables ORDINE = OrdineTables.ORDINE;

    /**
     * The table <code>PIATTO</code>.
     */
    public final PiattoTables PIATTO = PiattoTables.PIATTO;

    /**
     * The table <code>PIATTO_ORDINATO</code>.
     */
    public final PiattoOrdinatoTables PIATTO_ORDINATO = PiattoOrdinatoTables.PIATTO_ORDINATO;

    /**
     * The table <code>RESOCONTO_TAVOLO</code>.
     */
    public final ResocontoTavoloTables RESOCONTO_TAVOLO = ResocontoTavoloTables.RESOCONTO_TAVOLO;

    /**
     * The table <code>TAVOLO</code>.
     */
    public final TavoloTables TAVOLO = TavoloTables.TAVOLO;

    /**
     * No further instances allowed
     */
    private DefaultSchema() {
        super("", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.asList(
            ComponenteTables.COMPONENTE,
            CopertoTables.COPERTO,
            OrdineTables.ORDINE,
            PiattoTables.PIATTO,
            PiattoOrdinatoTables.PIATTO_ORDINATO,
            ResocontoTavoloTables.RESOCONTO_TAVOLO,
            TavoloTables.TAVOLO
        );
    }
}
