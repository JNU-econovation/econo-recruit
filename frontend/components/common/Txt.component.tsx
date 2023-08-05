import { FC, PropsWithChildren } from "react";
import classnames from "classnames";

const typographyType = {
  h6: "text-4xl font-bold",
  h5: "text-3xl font-bold",
  h4: "text-2xl font-bold",
  h3: "text-xl font-bold",
  h2: "text-lg font-bold",
  h1: "text-base font-bold",
  p: "text-base",
};

const colorType = {
  black: "text-black",
  gray: "text-gray-500",
  white: "text-white",
};

interface TxtProps {
  typography?: keyof typeof typographyType;
  color?: keyof typeof colorType;
  className: string;
}

const Txt: FC<PropsWithChildren<TxtProps>> = ({
  color = "black",
  typography = "p",
  children,
  className,
}) => (
  <span
    className={classnames(
      className,
      colorType[color],
      typographyType[typography]
    )}
  >
    {children}
  </span>
);

export default Txt;
