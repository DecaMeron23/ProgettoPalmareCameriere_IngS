/*
 * This file is generated by jOOQ.
 */
package model.generated.tables;


import java.util.function.Function;

import model.generated.DefaultSchema;
import model.generated.Keys;
import model.generated.tables.records.OrdineRecord;

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
public class OrdineTables extends TableImpl<OrdineRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>ORDINE</code>
     */
    public static final OrdineTables ORDINE = new OrdineTables();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<OrdineRecord> getRecordType() {
        return OrdineRecord.class;
    }

    /**
     * The column <code>ORDINE.NUM_ORDINE</code>.
     */
    public final TableField<OrdineRecord, Integer> NUM_ORDINE = createField(DSL.name("NUM_ORDINE"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>ORDINE.TAVOLO</code>.
     */
    public final TableField<OrdineRecord, Integer> TAVOLO = createField(DSL.name("TAVOLO"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>ORDINE.COUNTER_PIATTO_ORDINATO</code>.
     */
    public final TableField<OrdineRecord, Integer> COUNTER_PIATTO_ORDINATO = createField(DSL.name("COUNTER_PIATTO_ORDINATO"), SQLDataType.INTEGER, this, "");

    private OrdineTables(Name alias, Table<OrdineRecord> aliased) {
        this(alias, aliased, null);
    }

    private OrdineTables(Name alias, Table<OrdineRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>ORDINE</code> table reference
     */
    public OrdineTables(String alias) {
        this(DSL.name(alias), ORDINE);
    }

    /**
     * Create an aliased <code>ORDINE</code> table reference
     */
    public OrdineTables(Name alias) {
        this(alias, ORDINE);
    }

    /**
     * Create a <code>ORDINE</code> table reference
     */
    public OrdineTables() {
        this(DSL.name("ORDINE"), null);
    }

    public <O extends Record> OrdineTables(Table<O> child, ForeignKey<O, OrdineRecord> key) {
        super(child, key, ORDINE);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public UniqueKey<OrdineRecord> getPrimaryKey() {
        return Keys.ORDINE__PK_ORDINE;
    }

    @Override
    public OrdineTables as(String alias) {
        return new OrdineTables(DSL.name(alias), this);
    }

    @Override
    public OrdineTables as(Name alias) {
        return new OrdineTables(alias, this);
    }

    @Override
    public OrdineTables as(Table<?> alias) {
        return new OrdineTables(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public OrdineTables rename(String name) {
        return new OrdineTables(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public OrdineTables rename(Name name) {
        return new OrdineTables(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public OrdineTables rename(Table<?> name) {
        return new OrdineTables(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<Integer, Integer, Integer> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function3<? super Integer, ? super Integer, ? super Integer, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function3<? super Integer, ? super Integer, ? super Integer, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}