package com.timmist24.timsoresnstones.mineral;

import com.google.common.base.Optional;
import com.timmist24.timsoresnstones.texturing.Color;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import java.awt.image.BufferedImage;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class Mineral implements Comparable<Mineral>{
    public static final IProperty<Mineral> MINERAL_PROPERTY = new PropertyHelper<Mineral>("mineral_property", Mineral.class) {
        @Override
        public Collection<Mineral> getAllowedValues() {
            return MINERALS;
        }

        @Override
        public Optional<Mineral> parseValue(String value) {
            for(Mineral mineral: MINERALS){
                if(mineral.title.equals(value)){
                    return Optional.of(mineral);
                }
            }
            return Optional.absent();
        }

        @Override
        public String getName(Mineral value) {
            return value.title;
        }
    };
    private static final List<Mineral> MINERALS = new ArrayList<>();
    private static final Random RANDOM = new Random();
    private static final float WEIGHT_FACTOR = 0.33f;
    private static final int UNSTABILITY_OIL_THRESHOLD = 30;
    static {
        MINERALS.add(new Mineral("empty", false, 0.0f, (char) 255, Blocks.STONE, 0));
        MINERALS.addAll(InitMineralsMethod.BRUTE_FORCE.getInitMinerals().initiateMinerals());

    }
    public static Mineral getMineral(int i) {
        if(i>=MINERALS.size()||i<0){
            return MINERALS.get(0);
        }
        return MINERALS.get(i);
    }
    public static int numberOfMinerals(){
        return MINERALS.size();
    }
    public static int indexOf(Mineral mineral){
        for (int i=0; i< MINERALS.size(); i++){
            if(MINERALS.get(i).equals(mineral)){
                return i;
            }
        }
        return -1;
    }
    public static void SetUpCollor(){
        TextureManager manager = Minecraft.getMinecraft().renderEngine;
        manager.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        int levle = 0;
        int width = GL11.glGetTexLevelParameteri(GL11.GL_TEXTURE_2D, levle, GL11.GL_TEXTURE_WIDTH);
        int height = GL11.glGetTexLevelParameteri(GL11.GL_TEXTURE_2D, levle, GL11.GL_TEXTURE_HEIGHT);
        int size = width*height;
        IntBuffer buffer = BufferUtils.createIntBuffer(size);
        GL11.glGetTexImage(GL11.GL_TEXTURE_2D, levle, GL12.GL_BGRA,GL12.GL_UNSIGNED_INT_8_8_8_8_REV, buffer);
        int[] rgbArray = new int[size];
        buffer.get(rgbArray);
        BufferedImage bufferedImage = new BufferedImage(width, height, 2);
        bufferedImage.setRGB(0,0, width, height, rgbArray, 0, width);

        for(Mineral mineral: MINERALS){
            IBlockState state = mineral.parent.getBlockState().getValidStates().get(mineral.parentsMeta);
            Item test = Item.getItemFromBlock(mineral.parent);
            Item itemDropped = mineral.parent.getItemDropped(state,RANDOM,0) ;
            int itemDammage = mineral.parent.damageDropped(state);
            Color color = Color.extractColor(test, mineral.parentsMeta, bufferedImage, levle, mineral.equals(MINERALS.get(0)));
            setMineralValues(mineral, color, color);
        }
    }

    private static void setMineralValues(Mineral mineral,  Color... colors) {
        mineral.colors = colors;
    }

/**
 * 1. Conductivity
 * Thermal conductivity is a measure of the quantity of
 * heat that flows through a material. It is measured as
 * one degree per unit of time, per unit of cross-sectioned
 * area, per unit of length.  Materials with low thermal
 * conductivity may be used as insulators, those with high
 * thermal conductivity may be a heat sink.  Metals that
 * exhibit high thermal conductivity would be candidates for
 * use in applications like heat exchangers or refrigeration.
 * Low thermal conductivity materials may be used in high temperature
 * applications, but often high temperature components require high
 * thermal conductivity, so it is important to understand the environment.
 * Electrical conductivity is similar, measuring the quantity of electricity
 * that is transferred through a material of known cross-section and length.
 *
 * 2. Corrosion Resistance
 * Corrosion resistance describes a material’s ability to prevent
 * natural chemical or electro-chemical attack by atmosphere,
 * moisture or other agents. Corrosion takes many forms including
 * pitting, galvanic reaction, stress corrosion, parting, inter-granular,
 * and others (many of which will be discussed in other newsletter editions).
 * Corrosion resistance may be expressed as the maximum depth in mils to
 * which corrosion would penetrate in one year; it is based on a linear
 * extrapolation of penetration occurring during the lifetime of a given
 * test or service.  Some materials are intrinsically corrosion resistant,
 * while others benefit from the addition of plating or coatings.  Many metals
 * that belong to families that resist corrosion are not totally safe from it,
 * and are still subject to the specific environmental conditions where they operate.
 *
 * 3. Density
 * Density, often expressed as pounds per cubic inch, or grams per
 * cubic centimeter, etc., describes the mass of the alloy per unit
 * volume. The density of the alloy will determine how much a component
 * of a certain size will weigh. This factor is important in applications
 * like aerospace or automotive where weight is important. Engineers looking
 * for lower weight components may seek alloys that are less dense, but must
 * then consider the strength to weight ratio.  A higher density material like
 * steel might be chosen, for example, if it provides higher strength than a
 * lower density material.  Such a part could be made thinner so that less
 * material could help compensate for the higher density.
 *
 * 4. Ductility / Malleability
 * Ductility is the ability of a material to deform plastically (that is, stretch)
 * without fracturing and retain the new shape when the load is removed.
 * Think of it as the ability to stretch a given metal into a wire. Ductility
 * is often measured using a tensile test as a percentage of elongation, or the
 * reduction in the cross sectional area of the sample before failure. A tensile
 * test can also be used to determine the Young’s Modulus or modulus of elasticity,
 * an important stress/strain ratio used in many design calculations.  The tendency
 * of a material to resist cracking or breaking under stress makes ductile materials
 * appropriate for other metalworking processes including rolling or drawing.
 * Certain other processes like cold-working tend to make a metal less ductile.
 *
 * Malleability, a physical property, describes a metal’s ability to be formed
 * without breaking. Pressure, or compressive stress, is used to press or roll
 * the material into thinner sheets.  A material with high malleability will be
 * able to withstand higher pressure without breaking.
 *
 * 5. Elasticity, Stiffness
 * Elasticity describes a material’s tendency to return to its original size and
 * shape when a distorting force is removed. As opposed to materials that exhibit
 * plasticity (where the change in shape is not reversible), an elastic material
 * will return to its previous configuration when the stress is removed.
 *
 * The stiffness of a metal is often measured by the Young’s Modulus, which compares
 * the relationship between stress (the force applied) and strain (the resulting deformation).
 * The higher the Modulus – meaning greater stress results in proportionally lesser deformation –
 * the stiffer the material.  Glass would be an example of a stiff/high Modulus material,
 * where rubber would be a material that exhibits low stiffness/low Modulus.  This is an
 * important design consideration for applications where stiffness is required under load.
 *
 * 6. Fracture Toughness
 * Impact resistance is a measure of a material’s ability to withstand a shock. The effect
 * of impact – a collision that occurs in a short period of time – is typically greater than
 * the effect of a weaker force delivered over a longer period.  So a consideration of impact
 * resistance should be included when the application includes an elevated risk of impact.
 * Certain metals may perform acceptably under static load but fail under dynamic loads or
 * when subjected to a collision.  In the lab, impact is often measured through a common Charpy
 * test, where a weighted pendulum strikes a sample opposite of machined V-notch.
 *
 * 7. Hardness
 * Hardness is defined as a material’s ability to resist permanent indentation
 * (that is plastic deformation). Typically, the harder the material, the better
 * it resists wear or deformation. The term hardness, thus, also refers to local
 * surface stiffness of a material or its resistance to scratching, abrasion, or
 * cutting.  Hardness is measured by employing such methods as Brinell, Rockwell,
 * and Vickers, which measure the depth and area of a depression by a harder material,
 * including a steel ball, diamond, or other indenter.
 *
 * 8. Plasticity
 * Plasticity, the converse of elasticity, describes the tendency of a certain solid
 * material to hold its new shape when subjected to forming forces. It is the quality
 * that allows materials to be bent or worked into a permanent new shape.  Materials
 * transition from elastic behavior to plastic at the yield point.
 *
 * 9. Strength – Fatigue
 * Fatigue can lead to fracture under repeated or fluctuating stresses (for example loading
 * or unloading) that have a maximum value less than the tensile strength of the material.
 * Higher stresses will accelerate the time to failure, and vice versa, so there is a
 * relationship between the stress and cycles to failure.  Fatigue limit, then, refers to
 * the maximum stress the metal can withstand (the variable) in a given number of cycles.
 * Conversely, the fatigue life measure holds the load fixed and measures how many load cycles
 * the material can withstand before failure.  Fatigue strength is an important consideration
 * when designing components subjected to repetitive load conditions.
 *
 * 10. Strength – Shear
 * Shear strength is a consideration in applications like bolts or beams where the direction
 * as well as the magnitude of the stress is important. Shear occurs when directional forces
 * cause the internal structure of the metal to slide against itself, at the granular level.
 *
 * 11. Strength – Tensile
 * One of the most common metal property measures is Tensile, or Ultimate, Strength. Tensile
 * strength refers to the amount of load a section of metal can withstand before it breaks.
 * In lab testing, the metal will elongate but return to its original shape through the area
 * of elastic deformation. When it reaches the point of permanent or plastic deformation
 * (measured as Yield), it retains the elongated shape even when load is removed.  At the
 * Tensile point, the load causes the metal to ultimately fracture.  This measure helps
 * differentiate between materials that are brittle from those that are more ductile. Tensile
 * or ultimate tensile strength is measured in Newtons per square millimeter (Mega Pascals or
 * MPa) or pounds per square inch.
 *
 * 12. Strength – Yield
 * Similar in concept and measure to Tensile Strength, Yield Strength describes the point
 * after which the material under load will no longer return to its original position or shape.
 * Deformation moves from elastic to plastic.  Design calculations include the Yield Point to
 * understand the limits of dimensional integrity under load.  Like Tensile strength, Yield
 * strength is measured in Newtons per square millimeter (Mega Pascals or MPa) or pounds per
 * square inch.
 *
 * 13. Toughness
 * Measured using the Charpy impact test similar to Impact Resistance, toughness represents a
 * material’s ability to absorb impact without fracturing at a given temperature. Since impact
 * resistance is often lower at low temperatures, materials may become more brittle.  Charpy
 * values are commonly prescribed in ferrous alloys where the possibilities of low temperatures
 * exist in the application (e.g. offshore oil platforms, oil pipelines, etc.) or where instantaneous
 * loading is a consideration (e.g. ballistic containment in military or aircraft applications).
 *
 * 14. Wear Resistance
 * Wear resistance is a measure of a material’s ability to withstand the effect of two materials
 * rubbing against each other. This can take many forms including adhesion, abrasion, scratching,
 * gouging, galling, and others.  When the materials are of different hardness, the softer metal
 * can begin to show the effects first, and management of that may be part of the design.  Even
 * rolling can cause abrasion because of the presence of foreign materials.  Wear resistance may
 * be measured as the amount of mass lost for a given number of abrasion cycles at a given load.
 *
 * 15 Magic Conductivity: fanticy property
 * Magic Conductivity is a fictional property that
 *
 * 16 magnetic
 *
 * Considering this information about mechanical and physical properties can promote an optimized
 * metal selection for a given application. Because of the multitude of materials available – and
 * the ability to modify properties through alloying and often through heat treatment efforts – it
 * can be time well spent to consult with metallurgical experts to select the material that provides
 * the needed performance balanced with cost-effectiveness.
 */
    /**
     *  1 *Conductivity
     *  2 Corrosion Resistance
     *  3 Density
     *  4 *Ductility / *Malleability
     *  6 *Elasticity / Stiffness
     *  6 Fracture Toughness
     *  7 *Hardness
     *  8 *Plasticity
     *  9 /*Strength, Fatigue
     * 10 Strength, Shear
     * 11 **Strength, Tensile
     * 12 Strength, Yield/*
     * 13 *Toughness
     * 14 Wear Resistance
     * 15 Magic Conductivity
     * 16 magnetic
     */
    public final String title;
    public Color[] colors;
    private MineralVariant type;
    //private final float weightPerUnit;
    //private final short specialProperties; //8000 -> is oil soluable, FF00 -> strength, 00FF ->rate //speed can be a function of hardness,weight
    private final Block parent;
    private final int parentsMeta;
    Mineral(String title, boolean isOilSoluble, float weightPerUnit, char stability, Block block, int meta){
        this.title = title;
        //this.stability = stability;
        //this.isOilSoluble = isOilSoluble;
        //this.weightPerUnit = weightPerUnit;
        this.parent = block;
        this.parentsMeta = meta;
        //specialProperties = 0;
    }





    public MineralVariant getType() {
        return type;
    }
    @Override
    public String toString(){
       return "Mineral:"+title+"<"+type+">";
    }
    @Override
    public int compareTo(Mineral other) {
        return title.compareTo(other.title);
    }
    @Override
    public boolean equals(Object other){
        if(other.getClass().equals(Mineral.class)) {
            return title.equals(((Mineral)other).title);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }
}
