/*
 * This file is generated by jOOQ.
 */
package model.generated;


import model.generated.tables.OrdineTables;
import model.generated.tables.PiattoTables;
import model.generated.tables.PiattoOrdinatoTables;
import model.generated.tables.ResocontoTavoloTables;
import model.generated.tables.TavoloTables;
import model.generated.tables.records.OrdineRecord;
import model.generated.tables.records.PiattoOrdinatoRecord;
import model.generated.tables.records.PiattoRecord;
import model.generated.tables.records.ResocontoTavoloRecord;
import model.generated.tables.records.TavoloRecord;

import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables in the
 * default schema.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<OrdineRecord> ORDINE__PK_ORDINE = Internal.createUniqueKey(OrdineTables.ORDINE, DSL.name("pk_ORDINE"), new TableField[] { OrdineTables.ORDINE.NUM_ORDINE, OrdineTables.ORDINE.TAVOLO }, true);
    public static final UniqueKey<PiattoRecord> PIATTO__PK_PIATTO = Internal.createUniqueKey(PiattoTables.PIATTO, DSL.name("pk_PIATTO"), new TableField[] { PiattoTables.PIATTO.NOME, PiattoTables.PIATTO.COMPONENTE }, true);
    public static final UniqueKey<PiattoOrdinatoRecord> PIATTO_ORDINATO__PK_PIATTO_ORDINATO = Internal.createUniqueKey(PiattoOrdinatoTables.PIATTO_ORDINATO, DSL.name("pk_PIATTO_ORDINATO"), new TableField[] { PiattoOrdinatoTables.PIATTO_ORDINATO.NUM_PIATTO, PiattoOrdinatoTables.PIATTO_ORDINATO.NUM_ORDINE, PiattoOrdinatoTables.PIATTO_ORDINATO.TAVOLO }, true);
    public static final UniqueKey<ResocontoTavoloRecord> RESOCONTO_TAVOLO__PK_RESOCONTO_TAVOLO = Internal.createUniqueKey(ResocontoTavoloTables.RESOCONTO_TAVOLO, DSL.name("pk_RESOCONTO_TAVOLO"), new TableField[] { ResocontoTavoloTables.RESOCONTO_TAVOLO.TAVOLO }, true);
    public static final UniqueKey<TavoloRecord> TAVOLO__PK_TAVOLO = Internal.createUniqueKey(TavoloTables.TAVOLO, DSL.name("pk_TAVOLO"), new TableField[] { TavoloTables.TAVOLO.NOME }, true);
}
