/*
 * This file is generated by jOOQ.
 */
package model.generated.tables;


import java.util.function.Function;

import model.generated.DefaultSchema;
import model.generated.Keys;
import model.generated.tables.records.PiattoRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function3;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row3;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PiattoTables extends TableImpl<PiattoRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>PIATTO</code>
     */
    public static final PiattoTables PIATTO = new PiattoTables();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<PiattoRecord> getRecordType() {
        return PiattoRecord.class;
    }

    /**
     * The column <code>PIATTO.NOME</code>.
     */
    public final TableField<PiattoRecord, String> NOME = createField(DSL.name("NOME"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>PIATTO.PREZZO</code>.
     */
    public final TableField<PiattoRecord, Double> PREZZO = createField(DSL.name("PREZZO"), SQLDataType.DOUBLE, this, "");

    /**
     * The column <code>PIATTO.COMPONENTE</code>.
     */
    public final TableField<PiattoRecord, String> COMPONENTE = createField(DSL.name("COMPONENTE"), SQLDataType.CLOB, this, "");

    private PiattoTables(Name alias, Table<PiattoRecord> aliased) {
        this(alias, aliased, null);
    }

    private PiattoTables(Name alias, Table<PiattoRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>PIATTO</code> table reference
     */
    public PiattoTables(String alias) {
        this(DSL.name(alias), PIATTO);
    }

    /**
     * Create an aliased <code>PIATTO</code> table reference
     */
    public PiattoTables(Name alias) {
        this(alias, PIATTO);
    }

    /**
     * Create a <code>PIATTO</code> table reference
     */
    public PiattoTables() {
        this(DSL.name("PIATTO"), null);
    }

    public <O extends Record> PiattoTables(Table<O> child, ForeignKey<O, PiattoRecord> key) {
        super(child, key, PIATTO);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public UniqueKey<PiattoRecord> getPrimaryKey() {
        return Keys.PIATTO__PK_PIATTO;
    }

    @Override
    public PiattoTables as(String alias) {
        return new PiattoTables(DSL.name(alias), this);
    }

    @Override
    public PiattoTables as(Name alias) {
        return new PiattoTables(alias, this);
    }

    @Override
    public PiattoTables as(Table<?> alias) {
        return new PiattoTables(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public PiattoTables rename(String name) {
        return new PiattoTables(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public PiattoTables rename(Name name) {
        return new PiattoTables(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public PiattoTables rename(Table<?> name) {
        return new PiattoTables(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<String, Double, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function3<? super String, ? super Double, ? super String, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function3<? super String, ? super Double, ? super String, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
