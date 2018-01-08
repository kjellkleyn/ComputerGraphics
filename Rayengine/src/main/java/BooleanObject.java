/**
 * Created by kjell on 11/12/2017.
 */
public class BooleanObject extends Object{

    public Object objectOne;
    public Object objectTwo;
    int type;


    public static final int INTERSECTION = 0;
    public static final int UNION = 1;
    public static final int DIFFERENCE = 2;



    BooleanObject(Object one, Object two, int type){
        this.objectOne = one;
        this.objectTwo = two;
        this.type = type;
    }

    public RayHit Hit(Ray ray){
       switch (type){
           case INTERSECTION: return intersection(ray);
           case UNION: return union(ray);
           case DIFFERENCE: return difference(ray);
           default: return union(ray);
       }
    }

    private RayHit union(Ray ray){
        RayHit hitOne = objectOne.Hit(ray);
        RayHit hitTwo = objectTwo.Hit(ray);

        if(hitOne.getIsHit() && hitTwo.getIsHit()){

            if(hitOne.getT() < hitTwo.getT()){
                RayHit rayHit = new RayHit(hitOne.getHitPos().getX(),hitOne.getHitPos().getY(),hitOne.getHitPos().getZ(),hitOne.getT());
                rayHit.setNormVec(hitOne.getNormVec());
                rayHit.setM1(hitOne.getNormVec());
                rayHit.setT1( hitOne.getT() );
                rayHit.setMaterial( hitOne.getMaterial() );
                rayHit.setHitPos1( rayHit.getHitPos() );

                rayHit.setM2( hitTwo.m2 );
                rayHit.setT2( hitTwo.t2 );
                rayHit.setHitPos2( hitTwo.hitPos2 );



                return rayHit;
            }else{
                RayHit rayHit = new RayHit(hitTwo.getHitPos().getX(),hitTwo.getHitPos().getY(),hitTwo.getHitPos().getZ(),hitTwo.getT());
                rayHit.setNormVec(hitTwo.getNormVec());
                rayHit.setM1(hitTwo.getNormVec());
                rayHit.setT1( hitTwo.getT() );
                rayHit.setHitPos1( rayHit.getHitPos());
                rayHit.setMaterial( hitTwo.getMaterial() );

                rayHit.setM2( hitOne.m2 );
                rayHit.setT2( hitOne.t2 );
                rayHit.setHitPos2( hitOne.hitPos2 );



                return rayHit;
            }
        }else if(hitOne.getIsHit()){
            return hitOne;
        }else if(hitTwo.getIsHit()){
            return hitTwo;
        }else{
            return new RayHit();
        }


    }

    private RayHit intersection(Ray ray){
        RayHit hitOne = objectOne.Hit(ray);
        RayHit hitTwo = objectTwo.Hit(ray);

        /*
        if(hitOne.isHit && hitTwo.isHit){
            System.out.println("Object 1 : t1: " + hitOne.t1 + " t2 " + hitOne.t2);
            System.out.println("Object 2 : t1: " + hitTwo.t1 + " t2 " + hitTwo.t2);
            System.out.println();
        }
*/


        if( (hitOne.isHit && !hitTwo.isHit) || (!hitOne.isHit && hitTwo.isHit) )
            return new RayHit( false );
        else if( hitOne.isHit && hitTwo.isHit) {
            // check cases
            if( hitTwo.t1 < hitOne.t1 && hitTwo.t2 < hitOne.t1 )
                return new RayHit(false);
            else if( hitTwo.t1 > hitOne.t2 && hitTwo.t2 > hitOne.t2 )
                return new RayHit( false );
            else if( hitTwo.t1 > hitOne.t1 && hitTwo.t2 < hitOne.t2) {
                return hitTwo;
            }
            else if( hitTwo.t1 < hitOne.t1 && hitTwo.t2 > hitOne.t2 ) {
                return hitOne;
            }
            else if( hitTwo.t1 < hitOne.t1 && hitTwo.t2 < hitOne.t2) {
                if( hitOne.t1 > 0.000001) {
                    RayHit rayHit = new RayHit(hitOne.hitPos1.getX(), hitOne.hitPos1.getY(), hitOne.hitPos1.getZ(), hitOne.t1);

                    rayHit.setNormVec(hitOne.m1);
                    rayHit.setMaterial(hitOne.material);
                    rayHit.setM1(hitOne.m1);
                    rayHit.setT1(hitOne.t1);
                    rayHit.setHitPos1(hitOne.hitPos1);
                    rayHit.setM2(hitTwo.m2);
                    rayHit.setT2(hitTwo.t2);
                    rayHit.setHitPos2(hitTwo.hitPos2);

                    return rayHit;
                }else if(hitTwo.t2 > 0.0000001) {
                    RayHit rayHit = new RayHit(hitTwo.hitPos2.getX(), hitTwo.hitPos2.getY(), hitTwo.hitPos2.getZ(), hitTwo.t2);

                    rayHit.setNormVec(hitTwo.m2);
                    rayHit.setMaterial(hitTwo.material);
                    rayHit.setM1(hitOne.m1);
                    rayHit.setT1(hitOne.t1);
                    rayHit.setHitPos1(hitOne.hitPos1);
                    rayHit.setM2(hitTwo.m2);
                    rayHit.setT2(hitTwo.t2);
                    rayHit.setHitPos2(hitTwo.hitPos2);

                    return rayHit;
                } else {
                    return new RayHit( false );
                }
            }
            else if(hitTwo.t1 > hitOne.t1 & hitTwo.t2 > hitOne.t2) {
                if( hitTwo.t1 > 0.00001) {
                    RayHit rayHit = new RayHit(hitTwo.hitPos1.getX(), hitTwo.hitPos1.getY(), hitTwo.hitPos1.getZ(), hitTwo.t1);

                    rayHit.setNormVec(hitTwo.m1);
                    rayHit.setMaterial(hitTwo.material);
                    rayHit.setM1(hitTwo.m1);
                    rayHit.setT1(hitTwo.t1);
                    rayHit.setHitPos1(hitTwo.hitPos1);
                    rayHit.setM2(hitOne.m2);
                    rayHit.setT2(hitOne.t2);
                    rayHit.setHitPos2(hitOne.hitPos2);

                    return rayHit;
                } else if( hitOne.t2 > 0.000001 ) {
                    RayHit rayHit = new RayHit(hitOne.hitPos2.getX(), hitOne.hitPos2.getY(), hitOne.hitPos2.getZ(), hitOne.t2);

                    rayHit.setNormVec(hitOne.m2);
                    rayHit.setMaterial(hitOne.material);
                    rayHit.setM1(hitTwo.m1);
                    rayHit.setT1(hitTwo.t1);
                    rayHit.setHitPos1(hitTwo.hitPos1);
                    rayHit.setM2(hitOne.m2);
                    rayHit.setT2(hitOne.t2);
                    rayHit.setHitPos2(hitOne.hitPos2);

                    return rayHit;
                } else {
                    return new RayHit( false );
                }
            } else if( hitTwo.t1 == hitOne.t1 ) {
                return new RayHit( false );
            }
            else {
                return new RayHit( false );
            }
        } else {
            return new RayHit( false );
        }
    }

    private RayHit difference(Ray ray) {
        RayHit hitOne = objectOne.Hit(ray);
        RayHit hitTwo = objectTwo.Hit(ray);



        if(hitOne.isHit && !hitTwo.isHit){
            return hitOne;
        }else if(hitOne.isHit && hitTwo.isHit){

            if(hitTwo.t1 < hitOne.t1 && hitTwo.t2 < hitOne.t1){ //Object twee ligt volledig voor object één
                return hitOne;
            }else if(hitTwo.t1 > hitOne.t2 && hitTwo.t2 > hitOne.t2){ //Object twee ligt volledig achter object één
                return hitOne;
            }else if(hitTwo.t1 > hitOne.t1 && hitTwo.t2 < hitOne.t2){ //Object twee zit volledig in object één
                return hitOne;
            }else if(hitTwo.t1 < hitOne.t1 && hitTwo.t2 > hitOne.t2){ //Object één zit volledig in object twee
                return new RayHit();
            }else if(hitTwo.t1 < hitOne.t1 && hitTwo.t2 < hitOne.t2){ //Object twee en één raken elkaar. Object twee zit wel nog voor object één
                RayHit rayHit = new RayHit( hitTwo.hitPos2.getX(),hitTwo.hitPos2.getY(),hitTwo.hitPos2.getZ(),hitTwo.t2);
                rayHit.setNormVec( hitTwo.m2 );
                rayHit.setMaterial( hitTwo.getMaterial() );

                rayHit.setM1( rayHit.m2 );
                rayHit.setT1( rayHit.t2 );
                rayHit.setHitPos1( hitTwo.hitPos2 );

                rayHit.setM2( hitOne.m2 );
                rayHit.setT2( hitOne.t2 );
                rayHit.setHitPos2( hitOne.hitPos2 );

                return rayHit;
            }else if(hitTwo.t1 > hitOne.t1 && hitTwo.t2 > hitOne.t2){ //Object twee en één raken elkaar. Object twee zit wel achter object één
                RayHit rayHit = new RayHit( hitOne.getHitPos().getX(),hitOne.getHitPos().getY(),hitOne.getHitPos().getZ(),hitOne.getT());
                rayHit.setNormVec( hitOne.getNormVec() );
                rayHit.setMaterial( hitOne.getMaterial() );
                rayHit.setM1( hitOne.m1 );
                rayHit.setT1( hitOne.t1 );
                rayHit.setHitPos1( hitOne.hitPos1 );
                rayHit.setM2( hitTwo.m1 );
                rayHit.setT2( hitTwo.t1 );
                rayHit.setHitPos2( hitTwo.hitPos1 );

                return rayHit;
            }else{
                return new RayHit();
            }
        }else{
            return new RayHit();
        }

    }


}
