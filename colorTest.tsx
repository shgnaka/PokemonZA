import { Typography } from "@mui/material";
import { Box } from "@mui/system";

type ColorBlockProps = {
  year: number;
  month: number;
  day: number;
};

const ColorBlock = ({year , month, day }: ColorBlockProps) =>
{
  const insideOpenness = 0.99
  const outsideOpenness = 0.99
  return (
    <Box
      sx={{
        width: "100%",
        height: "100%",
        padding: "50%",
        borderRadius: 1,
        backgroundColor: `rgba(89, 141, 198, ${outsideOpenness})`,
        position: "relative",
      }}
    >
      <Box
        sx={{
          width: "65%",
          height: "65%",
          borderRadius: 1,
          backgroundColor: `rgba(227, 214, 95, ${insideOpenness})`,
          position: "absolute",
          top: "50%",
          left: "50%",
          transform: "translate(-50%, -50%)",
        }}
      >
        <Typography variant="subtitle2" component="h2">
          {month}
        </Typography>
      </Box>
    </Box>
  );
};

export default ColorBlock;