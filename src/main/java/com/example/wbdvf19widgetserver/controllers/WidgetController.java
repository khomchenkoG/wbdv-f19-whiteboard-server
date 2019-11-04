package com.example.wbdvf19widgetserver.controllers;

import com.example.wbdvf19widgetserver.models.Widget;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "*")
public class WidgetController {

    HashMap<String, List<Widget>> widgets = new HashMap<String, List<Widget>>();
    {
        Widget w1 = new Widget(Widget.Type.HEADING, 0, "", 1,"The document object model",
                "", "", "", "", true, "1234421"
                );
        Widget w2 = new Widget(Widget.Type.LIST, 0, "", 1,"",
                "Spring, Node, DataBases", "", "", "", true, "1221431"
        );
        List<Widget> widgetsFor34454 = new ArrayList<>();
        List<Widget> widgetsFor37854 = new ArrayList<>();

        widgetsFor37854.add(w2);
        widgetsFor34454.add(w1);
        widgets.put("34454", widgetsFor34454);
        widgets.put("37854", widgetsFor37854);



    }

    @PostMapping("/api/topics/{topicId}/widgets")
    public List<Widget> createWidget(
            @PathVariable("topicId") String topicId,
            @RequestBody Widget widget) {
        widget.setId(UUID.randomUUID().toString());
        widget.setIndex(widgets.get(topicId).size());
        widgets.get(topicId).add(widget);
        return widgets.get(topicId);
    }

    @PutMapping("/api/topics/{topicId}/widgets/{widgetId}")
    public List<Widget> updateWidget(
            @PathVariable("widgetId") String widgetId,
            @PathVariable("topicId") String topicId,
            @RequestBody Widget newWidget
    ) {
        List<Widget> widgetsForTopic = widgets.get(topicId);

        for(int i = 0; i < widgetsForTopic.size(); i++) {
            if(widgetsForTopic.get(i).getId().equals(widgetId)) {
                if (widgetsForTopic.get(i).getIndex() != newWidget.getIndex()){
                    moveWidgets(topicId, widgetId, newWidget, widgetsForTopic.get(i));
                }
                widgetsForTopic.set(i,newWidget);
            }
        }
         Collections.sort(widgetsForTopic);
        return widgetsForTopic;
    }

    private void moveWidgets(String topicId, String widgetId, Widget newWidget, Widget toMove) {
        if (toMove.getIndex() < newWidget.getIndex()){
            Widget nextWidget = widgets.get(topicId).get(toMove.getIndex() + 1);
            nextWidget.setIndex(nextWidget.getIndex()-1);
            toMove.setIndex(newWidget.getIndex());
        } else {
            Widget prevWidget =  widgets.get(topicId).get(toMove.getIndex() - 1);
            prevWidget.setIndex(prevWidget.getIndex()+1);
            toMove.setIndex(newWidget.getIndex());
        }
    }


    @DeleteMapping("/api/topics/{topicId}/widgets/{widgetId}")
    public List<Widget> deleteWidget(
            @PathVariable("widgetId") String widgetId,
            @PathVariable("topicId") String topicId) {

        List<Widget> widgetsForTopic = widgets.get(topicId);
        int index = -1;
        for(int i=0; i<widgetsForTopic.size(); i++) {
            if(widgetsForTopic.get(i).getId().equals(widgetId)){
                index = i;
            }
        }
        widgetsForTopic.remove(index);
        return widgetsForTopic;
    }

    @GetMapping("/api/topics/{topicId}/widgets")
    public List<Widget> findAllWidgets(@PathVariable("topicId") String topicId) {
        System.out.println("was herre for topic " + topicId);
        if (widgets.containsKey(topicId)){
            return widgets.get(topicId);
        } else {
            widgets.put(topicId, new ArrayList<>());
            return widgets.get(topicId);
        }
    }

    @GetMapping("/api/topics/{topicId}/widgets/{widgetId}")
    public Widget findWidgetById(
            @PathVariable("widgetId") String widgetId,
            @PathVariable("topicId") String topicId) {
        System.out.println("someone called me");
        for(Widget w: widgets.get(topicId)) {
            if(w.getId().equals(widgetId)) {
                return w;
            }
        }
        return null;
    }

    @GetMapping("/get/widget")
    public Widget getWidget() {

        return null;
    }
}