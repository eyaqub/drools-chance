/*
 * Copyright 2011 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.drools.chance.common.fact;


import org.drools.chance.common.*;
import org.drools.chance.common.trait.ImpBean;
import org.drools.chance.distribution.BasicDistribution;
import org.drools.chance.distribution.IDistribution;
import org.drools.chance.distribution.fuzzy.linguistic.LinguisticImperfectField;
import org.drools.chance.distribution.fuzzy.linguistic.ShapedFuzzyPartitionStrategyFactory;
import org.drools.chance.distribution.probability.BasicDistributionStrategyFactory;
import org.drools.chance.distribution.probability.dirichlet.DirichletDistributionStrategyFactory;
import org.drools.chance.distribution.probability.discrete.DiscreteDistributionStrategyFactory;


/**
 * Desired target for the generated Handle class, given the class Bean
 * and assuming its two fields "field" and "age" have been tagged as @Imperfect
 */
public class BeanImp implements ImpBean {


    // an "history field" for every @Imperfect field in the managed bean
    private IImperfectField<String> name_$$Imp = new ImperfectField<String>(
            ChanceStrategyFactory.<String>buildStrategies(
                    "probability",
                    "discrete",
                    "simple",
                    String.class)
    );



    private IImperfectField<Boolean> flag_$$Imp = new ImperfectField<Boolean>(
            ChanceStrategyFactory.<Boolean>buildStrategies(
                    "probability",
                    "discrete",
                    "simple",
                    Boolean.class),
            "true/0.66, false/0.34"
    );



    // an "history field" for every @Imperfect field in the managed bean
//	private IImperfectField<Integer> age;
    private IImperfectField<Integer> age_$$Imp = new ImperfectHistoryField<Integer>(
            ChanceStrategyFactory.<Integer>buildStrategies(
                    "probability",
                    "dirichlet",
                    "simple",
                    Integer.class),
            Integer.valueOf("3"),
            "18/0.02, 19/0.01, 20/0.04"
    );





    //    private IImperfectField<Weight> body;
    private IImperfectField<Weight> body_$$Imp = new LinguisticImperfectField<Weight,Double>(
            ChanceStrategyFactory.<Weight>buildStrategies(
                    "fuzzy",
                    "linguistic",
                    "simple",
                    Weight.class),
            ChanceStrategyFactory.<Double>buildStrategies(
                    "possibility",
                    "linguistic",
                    "simple",
                    Double.class),
            Integer.valueOf("3"),
            null
    );


    private IImperfectField<Cheese> likes_$$Imp = new ImperfectHistoryField<Cheese>(
            ChanceStrategyFactory.<Cheese>buildStrategies(
                    "probability",
                    "basic",
                    "simple",
                    Cheese.class),
            Integer.valueOf("2"),
            "cheddar/0.6"
    );


    private String  name;
    private Boolean flag;
    private Integer age;
    private Weight  body;
    private Double  weight;
    private Cheese  likes;




    // Inherited constructors
    public BeanImp() {

        age_$$Imp.setValue( new DirichletDistributionStrategyFactory<Integer>().buildStrategies( "simple", Integer.class).parse( "18/0.02, 19/0.01, 20/0.04" ),
                false );
        name_$$Imp.setValue( new DiscreteDistributionStrategyFactory<String>().buildStrategies("simple", String.class).parse("john/0.3, philip/0.7" ),
                false );
        body_$$Imp.setValue( new ShapedFuzzyPartitionStrategyFactory<Weight>().buildStrategies("simple", Weight.class).parse("SLIM/0.5, FAT/0.5" ),
                false );
        likes_$$Imp.setValue( new BasicDistributionStrategyFactory<Cheese>().buildStrategies("simple", Cheese.class).parse("cheddar/0.6" ),
                false );


        synchFields();
    }




    private void synchFields() {
        if (name != null)
            name_$$Imp.setValue(name);
        if (name_$$Imp != null)
            name = name_$$Imp.getCrisp();

        if (age != null)
            age_$$Imp.setValue(age);
        if (age_$$Imp != null)
            age = age_$$Imp.getCrisp();

        if (flag != null)
            flag_$$Imp.setValue(flag);
        if (flag_$$Imp != null)
            flag = flag_$$Imp.getCrisp();


        if (weight != null) {
            Double w = weight;

            IDistribution dist = ((LinguisticImperfectField<Weight,Double>) body_$$Imp).fuzzify(w);
            body_$$Imp.setValue(dist,false);
            body = body_$$Imp.getCrisp();

            weight = w;
        } else {
            if (body != null)
                body_$$Imp.setValue(body);
            if (body_$$Imp != null) {
                body = body_$$Imp.getCrisp();
                weight = ((LinguisticImperfectField<Weight,Double>) body_$$Imp).defuzzify();
            }
        }


        if (likes != null)
            likes_$$Imp.setValue(likes);
        if (likes_$$Imp != null)
            likes = likes_$$Imp.getCrisp();
    }






    public IImperfectField<String> getName() {
        return name_$$Imp;
    }

    public IDistribution<String> getNameDistr() {
        return name_$$Imp.getCurrent();
    }

    public String getNameValue() {
        return name;
    }


    public void setName(IImperfectField<String> x) {
        name_$$Imp = x;
        name = name_$$Imp.getCrisp();
    }

    public void updateName(IImperfectField<String> x) {
        name_$$Imp.update( x.getCurrent() );
        name = name_$$Imp.getCrisp();
    }




    public void setNameValue(String val) {
        name_$$Imp.setValue(val,false);
        name = name_$$Imp.getCrisp();
    }

    public void updateNameValue(String val) {
        name_$$Imp.setValue(val,true);
        name = name_$$Imp.getCrisp();
    }


    public void setNameDistr(IDistribution<String> field_dist) {
        name_$$Imp.setValue(field_dist,false);
        name = (name_$$Imp.getCrisp());	}

    public void updateNameDistr(IDistribution<String> field_bit) {
        name_$$Imp.update(field_bit);
        name = name_$$Imp.getCrisp();
    }




    public IImperfectField<Boolean> getFlag() {
        return flag_$$Imp;
    }

    public IDistribution<Boolean> getFlagDistr() {
        return flag_$$Imp.getCurrent();
    }

    public Boolean getFlagValue() {
        return flag;
    }

    public void setFlag(IImperfectField<Boolean> x) {
        flag_$$Imp = x;
        flag = flag_$$Imp.getCrisp();
    }

    public void setFlagDistr(IDistribution<Boolean> x) {
        flag_$$Imp.setValue( x, false );
        flag = flag_$$Imp.getCrisp();
    }

    public void setFlagValue(Boolean x) {
        flag_$$Imp.setValue( x, false );
        flag = flag_$$Imp.getCrisp();
    }

    public void updateFlag(IImperfectField<Boolean> x) {
        flag_$$Imp.update( x.getCurrent() );
        flag = flag_$$Imp.getCrisp();
    }

    public void updateFlagDistr(IDistribution<Boolean> x) {
        flag_$$Imp.update( x );
        flag = flag_$$Imp.getCrisp();
    }

    public void updateFlagValue(Boolean x) {
        flag_$$Imp.update( x );
        flag = flag_$$Imp.getCrisp();
    }










    // Extended getters and setters for field : age

    public IImperfectField<Integer> getAge() {
        return age_$$Imp;
    }

    public IDistribution<Integer> getAgeDistr() {
        return age_$$Imp.getCurrent();
    }

    public Integer getAgeValue() {
        return age;
    }


    public void setAge(IImperfectField<Integer> x) {
        age_$$Imp = x;
        age = age_$$Imp.getCrisp();
    }

    public void updateAge(IImperfectField<Integer> x) {
        age_$$Imp.update( x.getCurrent() );
        age = age_$$Imp.getCrisp();
    }




    public void setAgeValue(Integer val) {
        age_$$Imp.setValue(val,false);
        age = age_$$Imp.getCrisp();
    }

    public void updateAgeValue(Integer val) {
        age_$$Imp.setValue(val,true);
        age = age_$$Imp.getCrisp();
    }


    public void setAgeDistr(IDistribution<Integer> field_dist) {
        age_$$Imp.setValue(field_dist,false);
        age = (age_$$Imp.getCrisp());	}

    public void updateAgeDistr(IDistribution<Integer> field_bit) {
        age_$$Imp.update(field_bit);
        age = age_$$Imp.getCrisp();
    }








    public IImperfectField<Price> getPrice() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public IDistribution<Price> getPriceDistr() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Price getPriceValue() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setPrice(IImperfectField<Price> x) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setPriceDistr(IDistribution<Price> x) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setPriceValue(Price x) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void updatePrice(IDistribution<Price> x) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void updatePrice(Price x) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public Integer getBucks() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setBucks(Integer x) {
        //To change body of implemented methods use File | Settings | File Templates.
    }








    public IImperfectField<Cheese> getLikes() {
        return likes_$$Imp;
    }

    public IDistribution<Cheese> getLikesDistr() {
        return likes_$$Imp.getCurrent();
    }

    public Cheese getLikesValue() {
        return likes;
    }


    public void setLikes(IImperfectField<Cheese> x) {
        likes_$$Imp = x;
        likes = likes_$$Imp.getCrisp();
    }

    public void updateLikes(IImperfectField<Cheese> x) {
        likes_$$Imp.update( x.getCurrent() );
        likes = likes_$$Imp.getCrisp();
    }

    public void setLikesValue(Cheese val) {
        likes_$$Imp.setValue(val,false);
        likes = likes_$$Imp.getCrisp();
    }



    public void updateLikesValue(Cheese val) {
        likes_$$Imp.setValue(val,true);
        likes = likes_$$Imp.getCrisp();
    }


    public void setLikesDistr(IDistribution<Cheese> field_dist) {
        likes_$$Imp.setValue(field_dist,false);
        likes = (likes_$$Imp.getCrisp());	}

    public void updateLikesDistr(IDistribution<Cheese> field_bit) {
        likes_$$Imp.update(field_bit);
        likes = likes_$$Imp.getCrisp();
    }







    // Extended getters and setters for fuzzy field : body


    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double w) {
        IDistribution dist = ((LinguisticImperfectField<Weight,Double>) body_$$Imp).fuzzify(w);
        body_$$Imp.setValue(dist,false);
        body = body_$$Imp.getCrisp();

        weight = w;
    }

    public void updateWeight(Double w) {
        IDistribution dist = ((LinguisticImperfectField<Weight,Double>) body_$$Imp).fuzzify(w);
        body_$$Imp.setValue(dist,false);
        body = body_$$Imp.getCrisp();

        weight = ((LinguisticImperfectField<Weight, Double>) body_$$Imp).defuzzify();
    }




    public IDistribution<Weight> getBodyDistr() {
        return body_$$Imp.getCurrent();
    }

    public Weight getBodyValue() {
        return body;
    }

    public void setBody(IImperfectField<Weight> x) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setBodyDistr(IDistribution<Weight> x) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setBodyValue(Weight x) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setBody(Weight val) {
        body = val;
        this.body_$$Imp.setValue(val,false);

        weight = ((LinguisticImperfectField<Weight,Double>) body_$$Imp).defuzzify();
    }

    public void updateBody(Weight val) {
        body = val;
        this.body_$$Imp.setValue(val,true);

        weight = ((LinguisticImperfectField<Weight,Double>) body_$$Imp).defuzzify();
    }



    public void setBody(IDistribution<Weight> body_dist) {
        body_$$Imp.setValue(body_dist,true);
        body = body_$$Imp.getCrisp();

        weight = ((LinguisticImperfectField<Weight,Double>) body_$$Imp).defuzzify();
    }

    public void updateBody(IDistribution<Weight> body_bit) {
        body_$$Imp.update(body_bit);
        body = body_$$Imp.getCrisp();


        weight = ((LinguisticImperfectField<Weight,Double>) body_$$Imp).defuzzify();
    }


    public IImperfectField<Weight> getBody() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }



    @Override
    public String toString() {
        return "BeanImp - HC {" +
                "name_$$Imp=" + name_$$Imp +
                ", age_$$Imp=" + age_$$Imp +
                ", body_$$Imp=" + body_$$Imp +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", body=" + body +
                ", weight=" + weight +
                '}';
    }
}