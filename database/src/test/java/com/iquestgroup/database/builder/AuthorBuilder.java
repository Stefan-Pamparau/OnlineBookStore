package com.iquestgroup.database.builder;

import com.iquestgroup.model.Author;

/**
 * Builder used to create {@link com.iquestgroup.model.Author} instances.
 *
 * @author Stefan Pamparau
 */
public class AuthorBuilder {
    private Integer id;

    private String name;

    private Integer age;

    public AuthorBuilder setId(Integer id) {
        this.id = id;
        return this;
    }

    public AuthorBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public AuthorBuilder setAge(Integer age) {
        this.age = age;
        return this;
    }

    public Author build() {
        Author author = new Author();

        author.setId(id);
        author.setName(name);
        author.setAge(age);

        return author;
    }

    public Author build(Integer id, String name, Integer age) {
        Author author = new Author();

        author.setId(id);
        author.setName(name);
        author.setAge(age);

        return author;
    }
}
