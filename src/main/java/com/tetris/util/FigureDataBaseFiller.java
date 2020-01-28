package com.tetris.util;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.tetris.db.ConnectionFactory;
import com.tetris.db.entity.FigureType;
import com.tetris.game.Figure;
import com.tetris.model.Point;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FigureDataBaseFiller {

    private SessionFactory sessionFactory = ConnectionFactory.sessionFactory;


    @Test
    public void fill(){
        ObjectMapper objectMapper = new ObjectMapper();

        List<Point[]> figureStructures = new ArrayList<>();
        //z
        Point[] points = new Point[]{
                new Point(0, 0),
                new Point(1, 0),
                new Point(2, 0),
                new Point(0, 1),
                new Point(1, 1)
        };
        figureStructures.add(points);
        //z reverse
        points = new Point[]{
                new Point(0, 0),
                new Point(0, 0),
                new Point(1, 0),
                new Point(1, 1),
                new Point(1, 2)
        };
        figureStructures.add(points);
        // t
        points = new Point[]{
                new Point(0, 0),
                new Point(1, 0),
                new Point(0, 1),
                new Point(1, 1),
                new Point(2, 1)
        };
        figureStructures.add(points);
        // |
        points = new Point[]{
                new Point(0, 0),
                new Point(0, 0),
                new Point(0, 1),
                new Point(0, 2),
                new Point(0, 3)
        };
        figureStructures.add(points);
        //cube
        points = new Point[]{
                new Point(0, 0),
                new Point(0, 0),
                new Point(1, 0),
                new Point(0, 1),
                new Point(1, 1)
        };
        figureStructures.add(points);
        // L
        points = new Point[]{
                new Point(0, 0),
                new Point(0, 0),
                new Point(0, 1),
                new Point(1, 1),
                new Point(1, 2)
        };
        figureStructures.add(points);
        //L mirrored
        points = new Point[]{
                new Point(0, 0),
                new Point(0, 0),
                new Point(1, 0),
                new Point(0, 1),
                new Point(0, 2)
        };
        figureStructures.add(points);



        for (int i = 0; i < figureStructures.size(); i++) {
            try {
                String jsonObj = objectMapper.writeValueAsString(figureStructures.get(i));
                FigureType figureType = new FigureType();
                figureType.setFigureStructure(jsonObj);

                Session session = sessionFactory.openSession();
                session.beginTransaction();
                session.save(figureType);
                session.getTransaction().commit();
            } catch (JsonProcessingException e) {
                System.err.println(e.getOriginalMessage());
            }

        }
    }
}
