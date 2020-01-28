package com.tetris.db.repositories.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.tetris.db.entity.FigureType;
import com.tetris.db.repositories.Repository;
import com.tetris.game.Figure;
import com.tetris.model.Point;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FigureTypeRepository implements Repository {

    private final List<Figure.FigureBuilder> figureTemplates;
    private final List<FigureType> dbFigureTypes;

    public FigureTypeRepository() throws JsonProcessingException {
        this.dbFigureTypes = loadFigureTypes();
        this.figureTemplates = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();
        Gson gson = new Gson();

        for (FigureType figureType : dbFigureTypes) {
            Figure.FigureBuilder figureBuilder = Figure.builder();
            String figureStructure = figureType.getFigureStructure();            ;

            List<Point> pointList = new ArrayList<>(Arrays.asList(gson.fromJson(figureStructure,Point[].class)));
            Point pivot = pointList.get(0);
            pointList.remove(pivot);

            figureBuilder.points(pointList)
                    .pivot(pivot);

            figureTemplates.add(figureBuilder);
        }
    }

    public List<Figure.FigureBuilder> getFigureTemplates() {
        return figureTemplates;
    }

    public FigureType convertToFigureType(Figure figure){
        for (int i = 0; i < figureTemplates.size(); i++) {
            if(figure.equals(figureTemplates.get(i)
                    .currentCoordinateOnBoard(figure
                            .getCurrentCoordinateOnBoard())
                    .build())){
                return dbFigureTypes.get(i);
            }
        }
        throw new IllegalArgumentException("Figure with that type is not exist");
    }

    public Figure.FigureBuilder convertToFigureBuilder(FigureType figureType){
        for (int i = 0; i < dbFigureTypes.size(); i++) {
            if(dbFigureTypes.get(i)
                    .getFigureStructure()
                    .equals(figureType.getFigureStructure())){
                return figureTemplates.get(i);
            }
        }
        throw new IllegalArgumentException("FigureType isn't exits");
    }

    private List<FigureType> loadFigureTypes(){
        try(Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM FigureType", FigureType.class).list();
        }
    }
}
