package com.biz.search.es.entity;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * 商品{@code ElasticSearch Entity}
 *
 * @author david-liu
 * @date 2017年04月12日
 * @reviewer
 */
@Document(indexName = "depotnextdoor_products", type = "product")
public class ProductEsEntity implements Serializable {

    private static final long serialVersionUID = -7782509752271841542L;

    @Id
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)
    private String id;

    @Field(type = FieldType.String, store = true)
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
