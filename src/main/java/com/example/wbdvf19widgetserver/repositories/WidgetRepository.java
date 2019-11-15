package com.example.wbdvf19widgetserver.repositories;

import com.example.wbdvf19widgetserver.models.Widget;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WidgetRepository
        extends CrudRepository<Widget, Integer> {

    @Query("select widget from Widget widget where widget.id=:widgetId")
    public Widget findWidgetById(@Param("widgetId") Integer id);

    @Query("select widget from Widget widget where widget.topic.id=:topicId")
    public List<Widget> findAllWidgetsForTopic(@Param("topicId") Integer id);
}

