package net.torocraft.toroquest.civilization;

import java.util.UUID;

import net.minecraft.nbt.NBTTagCompound;

public class Province {

	public UUID id;
	public String name;
	public boolean hasLord;
	public int chunkX;
	public int chunkZ;

	public int lowerVillageBoundX;
	public int upperVillageBoundX;
	public int lowerVillageBoundZ;
	public int upperVillageBoundZ;

	public int xLength;
	public int zLength;
	public int area;

	public CivilizationType civilization;

	public void readNBT(NBTTagCompound c) {
		id = uuid(c.getString("id"));
		chunkX = c.getInteger("chunkX");
		chunkZ = c.getInteger("chunkZ");
		name = c.getString("name");
		lowerVillageBoundX = c.getInteger("lX");
		upperVillageBoundX = c.getInteger("uX");
		lowerVillageBoundZ = c.getInteger("lZ");
		upperVillageBoundZ = c.getInteger("uZ");
		civilization = e(c.getString("civilization"));
		hasLord = c.getBoolean("hasLord");
		computeSize();
	}

	private UUID uuid(String s) {
		try {
			return UUID.fromString(s);
		} catch (Exception e) {
			return null;
		}
	}

	private CivilizationType e(String s) {
		try {
			return CivilizationType.valueOf(s);
		} catch (Exception e) {
			return null;
		}
	}

	public void addToBoundsAndRecenter(int newChunkX, int newChunkZ) {
		lowerVillageBoundX = Math.min(lowerVillageBoundX, newChunkX);
		upperVillageBoundX = Math.max(upperVillageBoundX, newChunkX);
		lowerVillageBoundZ = Math.min(lowerVillageBoundZ, newChunkZ);
		upperVillageBoundZ = Math.max(upperVillageBoundZ, newChunkZ);
		computeSize();
		recenter();
	}

	private void recenter() {
		chunkX = lowerVillageBoundX + xLength / 2;
		chunkZ = lowerVillageBoundZ + zLength / 2;
	}

	public void computeSize() {
		xLength = Math.abs(upperVillageBoundX - lowerVillageBoundX) + 1;
		zLength = Math.abs(upperVillageBoundZ - lowerVillageBoundZ) + 1;
		area = xLength * zLength;
	}

	public NBTTagCompound writeNBT() {
		NBTTagCompound c = new NBTTagCompound();
		c.setString("id", s(id));
		c.setString("civilization", s(civilization));
		c.setInteger("chunkX", chunkX);
		c.setInteger("chunkZ", chunkZ);
		c.setInteger("lX", lowerVillageBoundX);
		c.setInteger("uX", upperVillageBoundX);
		c.setInteger("lZ", lowerVillageBoundZ);
		c.setInteger("uZ", upperVillageBoundZ);
		c.setBoolean("hasLord", hasLord);
		if (name != null && name.trim().length() > 0) {
			c.setString("name", name);
		}
		return c;
	}

	private String s(UUID s) {
		try {
			return s.toString();
		} catch (Exception e) {
			return "";
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(name).append(" of ").append(s(civilization).toLowerCase());
		sb.append(" at [").append(chunkX * 16).append(",").append(chunkZ * 16).append("]");
		if (hasLord) {
			sb.append(" has lord");
		} else {
			sb.append(" no lord");
		}
		return sb.toString();
	}

	private String s(CivilizationType civ) {
		if (civ == null) {
			return "";
		}
		return civ.toString();
	}

	public double chunkDistanceSq(int toChunkX, int toChunkZ) {
		double dx = (double) chunkX - toChunkX;
		double dz = (double) chunkZ - toChunkZ;
		return dx * dx + dz * dz;
	}
}