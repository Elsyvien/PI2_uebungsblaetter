package info2.swarm;

/**
 * This class implements the swarm behavior following 
 * Reynolds three flocking principles. It provides a 
 * static function, which calculates a preferred steering
 * force for a given particle and its swarm based on the
 * three principles cohesion, alignment, and separation.
 * 
 * Reynolds, C. W. (1987) Flocks, Herds, and Schools:
 * A Distributed Behavioral Model, in Computer Graphics, 21(4) 
 * (SIGGRAPH '87 Conference Proceedings) pages 25-34.
 * 
 * https://www.red3d.com/cwr/papers/1987/boids.html
 */
public class SwarmTools {

    /**
     * This method calculate the movement force for a given particle
     * within a given swarm based on Reynold's flocking principle.
     * @param particle Instance of the particle of interest.
     * @param swarm The surrounding swarm.
     * @param distOuter, 
     * @param distInner,
     * @param maxSpeed,
     * @param maxForce,
     * @param cohesionRate,
     * @param alignmentRate,
     * @param separationRate
     * @returnReactive steering force.
     */
    public static Vector2d calculateFlockingForce(
        final Particle particle,
        final Swarm swarm,
        final double distOuter, 
        final double distInner,
        final double maxSpeed,
        final double maxForce,
        final double cohesionRate,
        final double alignmentRate,
        final double separationRate
    ) {
        final Vector2d meanNeighborPosition = new Vector2d();
        final Vector2d meanNeighborVelocity = new Vector2d();
        final Vector2d meanAntiNeighborVelocity = new Vector2d();
        //
        final double distOuter2 = distOuter * distOuter;
        final double distInner2 = distInner * distInner;
        //
        int neighborCount = 0;
        int closeNeighborCount = 0;
        //
        for (int i = 0; i < swarm.getNumParticles(); i++) {
            final Particle other = swarm.get(i);
            //
            // This social principle is used for particles of
            // the same group (denoted by tag == group id).
            //
            if (
                (other != null) && 
                (other != particle) &&
                (!other.isPredator())
            ) {
                //
                // calculate distance between particle and other
                // particle (we use squared distance to avoid
                // expensive sqrt operation)
                //
                final double dist2 = Vector2d.dist2(
                    particle.getPosition(),
                    other.getPosition()
                );
                //
                // Is particle within outer perception range
                // (it is a perceivable neighbor), accumulate
                // position and velocity of the neighbor.
                //
                if (dist2 < distOuter2) {
                    Vector2d.add(
                        meanNeighborPosition, 
                        other.getPosition(), 
                        meanNeighborPosition
                    );
                    Vector2d.add(
                        meanNeighborVelocity,
                        other.getVelocity(),
                        meanNeighborVelocity
                    );     
                    neighborCount++;
                }
                //
                // Is particle within the inner perception range
                // it is stored as well to generate a repelling force.
                //
                if (dist2 < distInner2) {
                    //
                    // Generate a vector point from the neighbor
                    // to the particle with length reciprocal with
                    // the distance. The closer -> the more the vector
                    // pushes away.
                    //
                    final Vector2d diff = Vector2d.sub(
                        particle.getPosition(), 
                        other.getPosition()
                    );
                    Vector2d.normalize(diff, diff);
                    final double idist = 1.0 / Math.sqrt(dist2); 
                    Vector2d.mul(diff, idist, diff);
                    Vector2d.add(
                        meanAntiNeighborVelocity, 
                        diff,
                        meanAntiNeighborVelocity
                    );
                    closeNeighborCount++;
                }
            }
        }
        //
        final Vector2d cohesionForce = new Vector2d();
        final Vector2d alignmentForce = new Vector2d();
        final Vector2d separationForce = new Vector2d();
        //
        if (neighborCount > 0) {
            //
            // calculate position mean.
            // 
            final double iscale = 1.0 / (double)(neighborCount);
            Vector2d.mul(
                meanNeighborPosition, iscale, 
                meanNeighborPosition
            );            
            //
            // use position to generate cohesion force
            // (a force that pull all particles towards their
            // neighborhood mean position).
            //
            final Vector2d targetVelocity = Vector2d.sub(
                meanNeighborPosition,
                particle.getPosition()
            );
            Vector2d.normalize(targetVelocity, targetVelocity);
            Vector2d.mul(targetVelocity, maxSpeed, targetVelocity);
            Vector2d.sub(
                targetVelocity, 
                particle.getVelocity(),
                cohesionForce
            );
            Vector2d.limit(cohesionForce, maxForce, cohesionForce);
            //
            // use current mean velocity vector to calculate
            // alignment force (a force that causes the particle
            // to point in the same direction a neighbors)
            //
            Vector2d.normalize(meanNeighborVelocity, meanNeighborVelocity);
            Vector2d.mul(meanNeighborVelocity, maxSpeed, meanNeighborVelocity);
            Vector2d.sub(
                meanNeighborVelocity,
                particle.getVelocity(),
                alignmentForce
            );
            Vector2d.limit(alignmentForce, maxForce, alignmentForce);
        }
        if (closeNeighborCount > 0) {
            //
            // calculate position mean.
            //
            final double iscale = 1.0 / (double)(closeNeighborCount);
            Vector2d.mul(
                meanAntiNeighborVelocity, iscale, 
                meanAntiNeighborVelocity
            );
            //
            // Finally calculate separation force based on "very close"
            // neighbors (the force pushes the particle away from them)
            //
            Vector2d.normalize(
                meanAntiNeighborVelocity, 
                meanAntiNeighborVelocity
            );
            Vector2d.mul(
                meanAntiNeighborVelocity, maxSpeed, 
                meanAntiNeighborVelocity
            );
            Vector2d.sub(
                meanAntiNeighborVelocity,
                particle.getVelocity(),
                separationForce
            );
            Vector2d.limit(separationForce, maxForce, separationForce);
        }
        //
        // scale all faces with individual rates and
        // sum them up.
        //
        Vector2d.mul(cohesionForce, cohesionRate, cohesionForce);
        Vector2d.mul(alignmentForce, alignmentRate, alignmentForce);
        Vector2d.mul(separationForce, separationRate, separationForce);
        //
        final Vector2d result = new Vector2d();
        Vector2d.add(cohesionForce, alignmentForce, result);
        Vector2d.add(result, separationForce, result);
        //
        Vector2d.limit(result, maxForce, result);
        //
        return result;
    }
    
}