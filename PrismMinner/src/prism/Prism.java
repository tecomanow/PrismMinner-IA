/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prism;

import helper.Dados;
import java.io.IOException;
import trainningSet.AttribList;
import trainningSet.Attribute;
import trainningSet.TrainningSet;
import trainningSet.Value;

/**
 *
 * @author MateusR
 */
public class Prism {

    public static void main(String[] args) throws IOException {
        TrainningSet trainningset = new TrainningSet();
        Dados d = new Dados();
        d.pegarAtributos(trainningset);
        d.pegarDados(trainningset);
        Value v = new Value("Hard");

        //System.out.println(at.name);
        //TrainningSet aux = trainningset.selectSet(at);
        //Attribute bestAtValue = aux.bestAtValue(v);
        //System.out.println(bestAtValue.name);
        //aux = aux.selectSet(bestAtValue);
        //Attribute bestAtValue2 = aux.bestAtValue(v);
        //aux = aux.selectSet(bestAtValue2);
        //for (Value v : attrList.getClassValues()) {
        Attribute at = trainningset.bestAtValue(v);

        Rules R = new Rules();
        do {
            at = trainningset.bestAtValue(v);
            R.addCondition(at, v);
            trainningset = trainningset.selectSet(at);
        } while (at.values.get(0).probability != 1);

        System.out.println(R.getRule());//}

    }

    public RulesList mine(AttribList attrList, TrainningSet set) throws IOException {
        RulesList rulesList = new RulesList();
        //Arqui vou percorrer a lista de valores de classe dos atributos
        //e armazenar temporariamente em v para fazer pegar alguma regra
        //uma vez que depois eu tenho que fazer as regras para todos os valores
        //de classes
        Attribute bestValue;
        TrainningSet tsAux;
        for (Value v : attrList.getClassValues()) {
            TrainningSet setOriginal = set.createClone();
            do {
                Rules R = new Rules();
                tsAux = set;
                do {
                    bestValue = set.bestAtValue(v);
                    R.addCondition(bestValue, v);
                    set = set.selectSet(bestValue);
                } while (bestValue.values.get(0).probability != 1);
                rulesList.addRules(R);
                set = tsAux.pruneSet(R);
            } while (tsAux.hasNoClassValue(v));

        }
        return null;

    }

}
